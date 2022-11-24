package directory;

import static directory.directoryHandlingUnit.getSkinName;
import static org.junit.Assert.*;

import org.junit.Test;

public class dirhandlingtest {

  @Test
  public void dirToSkinNameNeedsToReturSkinName() {
    assertEquals(
      "This should return morg+",
      getSkinName("/Users/ayman/Downloads/morg+green+skin"),
      "morg+green+skin"
    );
  }
}





