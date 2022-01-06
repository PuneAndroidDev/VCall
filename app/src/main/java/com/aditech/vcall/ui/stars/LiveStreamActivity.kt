package com.aditech.vcall.ui.stars

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aditech.vcall.R
import com.aditech.vcall.network.Constraints
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LiveStreamActivity : AppCompatActivity() {

    private val PERMISSION_REQ_ID = 22

    private val REQUESTED_PERMISSIONS = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA
    )

    // Fill the App ID of your project generated on Agora Console.
    private val appId = Constraints.APPID

    // Fill the channel name.
    private val channelName = Constraints.CHANNEL

    // Fill the temp token generated on Agora Console.
    private val token = Constraints.TOKEN

    private var mRtcEngine: RtcEngine? = null


    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(this, permission) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, requestCode)
            return false
        }
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_stream)
        if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
            checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID)
        ) {

        }
        initializeAndJoinChannel();
    }

    private fun setupRemoteVideo(uid: Int) {
        val container: FrameLayout = findViewById(R.id.remote_video_view_container)
        val surfaceView = RtcEngine.CreateRendererView(this)
        surfaceView.setZOrderMediaOverlay(true)
        container.addView(surfaceView)
        mRtcEngine!!.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid))
    }

    private val mRtcEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        // Listen for the remote host joining the channel to get the uid of the host.
        override fun onUserJoined(uid: Int, elapsed: Int) {

            CoroutineScope(Dispatchers.Main).launch {
                setupRemoteVideo(uid)
            }
        }
    }

    private fun initializeAndJoinChannel() {
        mRtcEngine = RtcEngine.create(this, appId, mRtcEventHandler)

        // For a live streaming scenario, set the channel profile as BROADCASTING.
        mRtcEngine?.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        // Set the client role as BORADCAST or AUDIENCE according to the scenario.
        mRtcEngine?.setClientRole(Constants.CLIENT_ROLE_BROADCASTER)

        // By default, video is disabled, and you need to call enableVideo to start a video stream.
        mRtcEngine?.enableVideo()
        val container: FrameLayout = findViewById(R.id.local_video_view_container)
        // Call CreateRendererView to create a SurfaceView object and add it as a child to the FrameLayout.
        val surfaceView = RtcEngine.CreateRendererView(this)
        container.addView(surfaceView)
        // Pass the SurfaceView object to Agora so that it renders the local video.
        mRtcEngine?.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0))

        // Join the channel with a token.
        mRtcEngine?.joinChannel(token, channelName, "", 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRtcEngine!!.leaveChannel()
        RtcEngine.destroy()
    }
}