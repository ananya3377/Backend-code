package com.estatevault.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HeartbeatService {
    private final EmailService emailService;

    public HeartbeatService(EmailService emailService){ this.emailService = emailService; }

    // run daily at 03:00
    @Scheduled(cron = "0 0 3 * * *")
    public void dailyCheckIn(){
        // In real app: find users that need heartbeat and send secure check-in
        emailService.send("ops@estatevault.local","Heartbeat","Daily heartbeat executed (demo)");
    }
}