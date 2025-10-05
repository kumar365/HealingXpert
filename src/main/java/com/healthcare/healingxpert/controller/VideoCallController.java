package com.healthcare.healingxpert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
public class VideoCallController {

    @PostMapping("/initiate")
    public ResponseEntity<String> initiateCall(@RequestParam String userId) {
        // Add logic to initiate call with specific user
        return ResponseEntity.ok("Call initiated with user: " + userId);
    }

    @PostMapping("/end")
    public ResponseEntity<String> endCall(@RequestParam String callId) {
        // Add logic to end the call
        return ResponseEntity.ok("Call ended");
    }
}