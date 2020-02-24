/*
 * Copyright (c) 2010-2020, sikuli.org, sikulix.com - MIT license
 */

package org.sikuli.script.test;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;
import org.sikuli.script.support.RunTime;
import org.sikuli.script.support.SXTest;
import org.sikuli.util.Highlight;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElementRegionTest extends SXTest {

  @Before
  public void setUp() {
    setUpBase();
    Settings.setImageCache(0);
  }

  @Test
  public void test000_StartUp() {
    startUpBase();
  }

  @Test
  public void test200_RegionFindOld() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Region reg = new Screen();
    Match match = null;
    try {
      match = reg.find(testName);
    } catch (FindFailed findFailed) {
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
    }
    testOutro("%s in %s is %s", testName, reg, match);
    Assert.assertTrue("Not found!", checkMatch(match, 0.95));
  }

  @Test
  public void test210_RegionFindTransOld() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Region reg = new Screen();
    Match match = null;
    try {
      match = reg.find(testNameTrans);
    } catch (FindFailed findFailed) {
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
    }
    testOutro("%s in %s is %s", testName, reg, match);
    Assert.assertTrue("Not found!", checkMatch(match, 0.95));
  }

  @Test
  public void test211_RegionFindMaskOld() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Region reg = new Screen();
    Match match = null;
    try {
      Image imageMasked = new Image(testName).mask(testNameMask);
      match = reg.find(imageMasked);
    } catch (FindFailed findFailed) {
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
    }
    testOutro("%s in %s is %s", testName, reg, match);
    Assert.assertTrue("Not found!", checkMatch(match, 0.95));
  }

  @Test
  public void test220_RegionFindAllOld() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Region reg = new Screen();
    Iterator<Match> matches = null;
    try {
      matches = reg.findAll(testName);
    } catch (FindFailed findFailed) {
    }
    Match match = null;
    int matchCount = 0;
    while (matches.hasNext()) {
      matchCount++;
      match = matches.next();
      if (showImage) {
        match.highlight();
      }
    }
    if (showImage) {
      Highlight.closeAll(3);
    }
    testOutro("%s in %s is %s (%d)", testName, reg, match, matchCount);
    Assert.assertNotNull("Not Found!", match);
  }

  @Test
  public void test250_RegionFind() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Settings.NewAPI = true;
    Region reg = new Screen();
    Match match = null;
    try {
      match = reg.find(testName);
    } catch (FindFailed findFailed) {
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
    }
    testOutro("%s in %s is %s", testName, reg, match);
    Assert.assertTrue("Not found!", checkMatch(match, 0.95));
  }

  @Test
  public void test251_RegionFindFailed() {
    testIntro();
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Settings.NewAPI = true;
    Region reg = new Screen();
    Match match = null;
    String error = "";
    try {
      match = reg.find(testName);
    } catch (FindFailed findFailed) {
      error = findFailed.getMessage();
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
      testOutro("%s in %s is %s", testName, reg, match);
    } else {
      testOutro("Not Found: %s in %s", testName, reg);
    }
    Assert.assertFalse("Should not be fond!", error.isEmpty());
  }

  @Test
  public void test252_RegionWait() {
    waitBefore = 2;
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Settings.NewAPI = true;
    Region reg = new Screen();
    Match match = null;
    try {
      match = reg.wait(testName, 5);
    } catch (FindFailed findFailed) {
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
    }
    testOutro("%s in %s is %s", testName, reg, match);
    Assert.assertTrue("Not found!", checkMatch(match, 0.95));
  }

  @Test
  public void test253_RegionFindAllIterator() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Settings.NewAPI = true;
    Region reg = new Screen();
    Iterator<Match> matches = null;
    int matchCount = 0;
    try {
      matches = reg.findAll(testName);
    } catch (FindFailed findFailed) {
    }
    Match match = null;
    while (matches.hasNext()) {
      matchCount++;
      match = matches.next();
      if (showImage) {
        match.highlight();
      }
    }
    if (showImage) {
      Highlight.closeAll(3);
    }
    testOutro("%s in %s is %s (%d)", testName, reg, match, matchCount);
    Assert.assertNotNull("Not Found!", match);
  }

  @Test
  public void test254_RegionFindAllList() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Settings.NewAPI = true;
    Region reg = new Screen();
    List<Match>  matches = null;
    int matchCount = 0;
    try {
      matches = reg.findAll(testName).asList();
    } catch (FindFailed findFailed) {
    }
    matchCount = matches.size();
    if (showImage) {
      for (Match match : matches) {
        match.highlight();
      }
      Highlight.closeAll(3);
    }
    testOutro("%s in %s found %d times", testName, reg, matchCount);
    Assert.assertTrue("Not Found!", matchCount == 3);
  }

  @Test
  public void test260_RegionFindTrans() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Settings.NewAPI = true;
    Region reg = new Screen();
    Match match = null;
    try {
      match = reg.find(testNameTrans);
    } catch (FindFailed findFailed) {
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
    }
    testOutro("%s in %s is %s", testNameTrans, reg, match);
    Assert.assertTrue("Not found!", checkMatch(match, 0.95));
  }

  @Test
  public void test261_RegionFindMask() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Settings.NewAPI = true;
    Region reg = new Screen();
    Match match = null;
    try {
      Image imageMasked = new Image(testName).mask(testNameMask);
      match = reg.find(imageMasked);
    } catch (FindFailed findFailed) {
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
    }
    testOutro("%s in %s is %s", testName, reg, match);
    Assert.assertTrue("Not found!", checkMatch(match, 0.95));
  }

  @Test
  public void test262_RegionFindIsMasked() {
    testIntro(testBase);
    Assume.assumeFalse("Running headless - ignoring test", RunTime.isHeadless());
    Settings.NewAPI = true;
    Region reg = new Screen();
    Match match = null;
    try {
      Image imageMasked = new Image(testNameMask).mask();
      match = reg.find(imageMasked);
    } catch (FindFailed findFailed) {
    }
    if (null != match) {
      if (showImage) {
        match.highlight(2);
      }
    }
    testOutro("%s in %s is %s", testName, reg, match);
    Assert.assertTrue("Not found!", checkMatch(match, 0.95));
  }

  @Test
  public void test999() {
    testIntro();
    Map<URL, List<Object>> cache = Image.getCache();
    testOutro("%s", Image.cacheStats());
  }
}