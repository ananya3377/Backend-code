package com.service;

import com.models.UnlockRequest;
import com.models.User;
import com.repository.UnlockRequestRepository;
import com.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UnlockService {
    private final UnlockRequestRepository unlockRepo;
    private final UserRepository userRepo;
    private final StorageService storageService;
    private final EmailService emailService;

    public UnlockService(UnlockRequestRepository unlockRepo, UserRepository userRepo, StorageService storageService, EmailService emailService){
        this.unlockRepo = unlockRepo; this.userRepo = userRepo; this.storageService = storageService; this.emailService = emailService;
    }

    public UnlockRequest createRequest(Long requesterUserId, MultipartFile file) throws IOException {
        Optional<User> opt = userRepo.findById(requesterUserId);
        if(opt.isEmpty()) throw new IllegalArgumentException("Requester user not found");
        User requester = opt.get();
        String path = storageService.store(file, "unlock_proofs");
        UnlockRequest r = new UnlockRequest();
        r.setRequester(requester);
        r.setStatus("PENDING");
        r.setUploadedDocPath(path);
        UnlockRequest saved = unlockRepo.save(r);
        // notify owner/co-verifier(s)
        emailService.send(requester.getEmail(), "Unlock request received", "Your unlock request is received and pending verification.");
        return saved;
    }

    public UnlockRequest approveByCoVerifier(Long requestId){
        UnlockRequest r = unlockRepo.findById(requestId).orElseThrow(() -> new IllegalArgumentException("Request not found"));
        r.setCoVerifierApproved(true);
        r.setStatus("VERIFIED");
        UnlockRequest saved = unlockRepo.save(r);
        // notify owner/admin
        emailService.send(saved.getRequester().getEmail(), "Unlock verified by co-verifier", "Co-verifier approved the request.");
        return saved;
    }

    public UnlockRequest adminApprove(Long requestId){
        UnlockRequest r = unlockRepo.findById(requestId).orElseThrow(() -> new IllegalArgumentException("Request not found"));
        r.setStatus("APPROVED");
        UnlockRequest saved = unlockRepo.save(r);
        emailService.send(saved.getRequester().getEmail(), "Unlock approved", "An admin approved the unlock. Follow handover steps.");
        return saved;
    }
}