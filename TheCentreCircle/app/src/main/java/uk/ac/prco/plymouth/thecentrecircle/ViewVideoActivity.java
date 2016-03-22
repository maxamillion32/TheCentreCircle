package uk.ac.prco.plymouth.thecentrecircle;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class ViewVideoActivity extends AppCompatActivity {
    VideoView videoView;
    MyMediaController videoController;
    private ShareActionProvider shareActionProvider;
    String videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);
        String domain = getIntent().getStringExtra("domain");
        videoUrl = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        //https://streamable.com/c2u1

        setupActionBar();

        if (!videoUrl.endsWith(".mp4")) {
            String[] array = videoUrl.split("/");
            String videoRef = array[3];
            if (domain.equals("streamable.com")) {
                videoUrl = "https://cdn.streamable.com/video/mp4/" + videoRef + ".mp4";
            } else if (domain.equals("gfycat.com")) {
                videoUrl = "https://zippy.gfycat.com/" + videoRef + ".mp4";
            }
        }

        videoView  = (VideoView) findViewById(R.id.videoView);

        FrameLayout controllerAnchor = (FrameLayout) findViewById(R.id.controllerAnchor);
        videoController = new MyMediaController(this, controllerAnchor);
        videoView.setVideoURI(Uri.parse(videoUrl));

        videoView.start();
        if (savedInstanceState != null) {
            int pos = savedInstanceState.getInt("pos");
            videoView.seekTo(pos);
        }
        videoController.setAnchorView(controllerAnchor);
        videoView.setMediaController(videoController);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pos", videoView.getCurrentPosition());
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_video, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_home) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, videoUrl);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyMediaController extends MediaController {
        private FrameLayout anchorView;

        public MyMediaController(Context context, FrameLayout anchorView) {
            super(context);
            this.anchorView = anchorView;
        }

        @Override
        protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
        {
            super.onSizeChanged(xNew, yNew, xOld, yOld);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) anchorView.getLayoutParams();
            lp.setMargins(0, 0, 0, yNew);

            anchorView.setLayoutParams(lp);
            anchorView.requestLayout();
        }
    }
}