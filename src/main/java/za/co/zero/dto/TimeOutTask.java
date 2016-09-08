package za.co.zero.dto;

import java.util.TimerTask;

/**
 * Created by NMandisa Mkhungo on 9/5/2016.
 */
public class TimeOutTask extends TimerTask {

    public boolean isTimedOut = false;

    public void run() {
        isTimedOut = true;
    }
}
