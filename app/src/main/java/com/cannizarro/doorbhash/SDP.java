package com.cannizarro.doorbhash;

import android.util.Log;

import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;

public class SDP {

    private String username;
    private String type;
    private String sdp;

    private int label;
    private String id;
    private String candidate;

    public SDP(IceCandidate iceCandidate){

        this.sdp = null;
        this.username = "Anonymous";
        this.type = "candidate";
        this.label = iceCandidate.sdpMLineIndex;
        this.id = iceCandidate.sdpMid;
        this.candidate = iceCandidate.sdp;

    }
    public SDP(IceCandidate iceCandidate, String username){

        this.sdp = null;
        this.username = username;
        this.type = "candidate";
        this.label = iceCandidate.sdpMLineIndex;
        this.id = iceCandidate.sdpMid;
        this.candidate = iceCandidate.sdp;

    }

    public SDP(SessionDescription message, String username) {
        this.username = username;
        this.type = message.type.canonicalForm();
        this.sdp = message.description;
        this.label = Integer.parseInt(null);
        this.id = null;
        this.candidate = null;

    }

    public SDP(SessionDescription message) {
        this.username = "Anonymous";
        this.type = message.type.canonicalForm();
        this.sdp = message.description;
        this.label = Integer.parseInt(null);
        this.id = null;
        this.candidate = null;
    }




}
