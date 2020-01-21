package com.cannizarro.doorbhash;

import android.util.Log;

import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;

public class SDP {

    public String username;
    public String type;
    public String sdp;

    public Integer label;
    public String id;
    public String candidate;

    /*public SDP(IceCandidate iceCandidate){

        this.sdp = null;
        this.username = "Anonymous";
        this.type = "candidate";
        this.label = iceCandidate.sdpMLineIndex;
        this.id = iceCandidate.sdpMid;
        this.candidate = iceCandidate.sdp;

    }*/

    public SDP(){

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
        this.label = null;
        this.id = null;
        this.candidate = null;

    }

    /*public SDP(SessionDescription message) {
        this.username = "Anonymous";
        this.type = message.type.canonicalForm();
        this.sdp = message.description;
        this.label = Integer.parseInt(null);
        this.id = null;
        this.candidate = null;
    }
*/



}
