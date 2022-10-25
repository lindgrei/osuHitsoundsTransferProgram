package directory;

import org.junit.Test;

import static directory.dirHandling.getSkinName;
import static org.junit.Assert.*;


public class dirhandlingtest {

    @Test
    public void dirToSkinNameNeedsToReturSkinName(){
        assertEquals("This should return morg+", getSkinName("/Users/ayman/Downloads/morg+green+skin"), "morg+green+skin" );
    }
}