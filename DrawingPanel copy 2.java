/*
Stuart Reges and Marty Stepp
February 24, 2007
Changes by Tom Bylander in 2010 (no anti-alias, repaint on sleep)
Changes by Tom Bylander in 2012 (track mouse clicks and movement)
Changes by Tom Bylander in 2013 (fix bug in tracking mouse clicks)
Changes by S. Robbins   in 2014 (getters for width and height)
Changes by S. Robbins   in 2014 (addKeyListener added)
Changes by S. Robbins   in 2014 (catch exception on default close so that it works in an applet)
Changes by S. Robbins   in 2015 (buffer key events)
Changes by S. Robbins   in 2015 (show mouse status by default is off)

The DrawingPanel class provides a simple interface for drawing persistent
images using a Graphics object.  An internal BufferedImage object is used
to keep track of what has been drawn.  A client of the class simply
constructs a DrawingPanel of a particular size and then draws on it with
the Graphics object, setting the background color if they so choose.

To ensure that the image is always displayed, a timer calls repaint at
regular intervals.
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class DrawingPanel implements ActionListener {
 private static final String versionMessage =
    "Drawing Panel version 1.1, January 25, 2015";
 private static final int DELAY = 100;  // delay between repaints in millis
 private static final boolean PRETTY = false;  // true to anti-alias
 private static boolean showStatus = false;
 private static final int MAX_KEY_BUF_SIZE = 10;

 private int width, height;    // dimensions of window frame
 private JFrame frame;         // overall window frame
 private JPanel panel;         // overall drawing surface
 private BufferedImage image;  // remembers drawing commands
 private Graphics2D g2;        // graphics context for painting
 private JLabel statusBar;     // status bar showing mouse position
 private volatile MouseEvent click;     // stores the last mouse click
 private volatile boolean pressed;      // true if the mouse is pressed
 private volatile MouseEvent move;      // stores the position of the mouse
 private ArrayList<KeyInfo> keys;

 // construct a drawing panel of given width and height enclosed in a window
 public DrawingPanel(int width, int height) {
   this.width = width;
   this.height = height;
   keys = new ArrayList<KeyInfo>();
   image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

   statusBar = new JLabel(" ");
   statusBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
   statusBar.setText(versionMessage);

   panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
   panel.setBackground(Color.WHITE);
   panel.setPreferredSize(new Dimension(width, height));
   panel.add(new JLabel(new ImageIcon(image)));

   click = null;
   move = null;
   pressed = false;

   // listen to mouse movement
   MouseInputAdapter listener = new MouseInputAdapter() {
     public void mouseMoved(MouseEvent e) {
       pressed = false;
       move = e;
       if (showStatus)
          statusBar.setText("moved (" + e.getX() + ", " + e.getY() + ")");
     }

     public void mousePressed(MouseEvent e) {
       pressed = true;
       move = e;
       if (showStatus)
          statusBar.setText("pressed (" + e.getX() + ", " + e.getY() + ")");
     }

     public void mouseDragged(MouseEvent e) {
       pressed = true;
       move = e;
       if (showStatus)
          statusBar.setText("dragged (" + e.getX() + ", " + e.getY() + ")");
     }

     public void mouseReleased(MouseEvent e) {
       click = e;
       pressed = false;
       if (showStatus)
          statusBar.setText("released (" + e.getX() + ", " + e.getY() + ")");
     }

     public void mouseEntered(MouseEvent e) {
//       System.out.println("mouse entered");
       panel.requestFocus();
     }

   };
   panel.addMouseListener(listener);
   panel.addMouseMotionListener(listener);
   new DrawingPanelKeyListener();

   g2 = (Graphics2D)image.getGraphics();
   g2.setColor(Color.BLACK);
   if (PRETTY) {
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     g2.setStroke(new BasicStroke(1.1f));
   }

   frame = new JFrame("Drawing Panel");
   frame.setResizable(false);
   try {
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // so that this works in an applet
   } catch (Exception e) {}
   frame.getContentPane().add(panel);
   frame.getContentPane().add(statusBar, "South");
   frame.pack();
   frame.setVisible(true);
   toFront();
   frame.requestFocus();

   // repaint timer so that the screen will update
   new Timer(DELAY, this).start();
 }

 public void showMouseStatus(boolean f) {
   showStatus = f;
 }

 public void addKeyListener(KeyListener listener) {
   panel.addKeyListener(listener);
   panel.requestFocus();
 }

 // used for an internal timer that keeps repainting
 public void actionPerformed(ActionEvent e) {
   panel.repaint();
 }

 // obtain the Graphics object to draw on the panel
 public Graphics2D getGraphics() {
   return g2;
 }

 // set the background color of the drawing panel
 public void setBackground(Color c) {
   panel.setBackground(c);
 }

 // show or hide the drawing panel on the screen
 public void setVisible(boolean visible) {
   frame.setVisible(visible);
 }

 // makes the program pause for the given amount of time,
 // allowing for animation
 public void sleep(int millis) {
   panel.repaint();
   try {
     Thread.sleep(millis);
   } catch (InterruptedException e) {}
 }

 // close the drawing panel
 public void close() {
   frame.dispose();
 }

 // makes drawing panel become the frontmost window on the screen
 public void toFront() {
   frame.toFront();
 }

 // return panel width
 public int getWidth() {
    return width;
 }

 // return panel height
 public int getHeight() {
    return height;
 }

 // return the X position of the mouse or -1
 public int getMouseX() {
   if (move == null) {
     return -1;
   } else {
     return move.getX();
   }
 }

 // return the Y position of the mouse or -1
 public int getMouseY() {
   if (move == null) {
     return -1;
   } else {
     return move.getY();
   }
 }

 // return the X position of the last click or -1
 public int getClickX() {
   if (click == null) {
     return -1;
   } else {
     return click.getX();
   }
 }

 // return the Y position of the last click or -1
 public int getClickY() {
   if (click == null) {
     return -1;
   } else {
     return click.getY();
   }
 }

 // return true if a mouse button is pressed
 public boolean mousePressed() {
   return pressed;
 }

 public synchronized int getKeyCode() {
   if (keys.size() == 0)
     return 0;
   return keys.remove(0).keyCode;
 }

  public synchronized char getKeyChar() {
   if (keys.size() == 0)
     return 0;
   return keys.remove(0).keyChar;
 }

  public synchronized int getKeysSize() {
    return keys.size();
  }

 private synchronized void insertKeyData(char c, int code) {
   keys.add(new KeyInfo(c,code));
   if (keys.size() > MAX_KEY_BUF_SIZE) {
     keys.remove(0);
//     System.out.println("Dropped key");
   }
 }

 private class KeyInfo {
   public int keyCode;
   public char keyChar;

   public KeyInfo(char keyChar, int keyCode) {
     this.keyCode = keyCode;
     this.keyChar = keyChar;
   }
 }

 private class DrawingPanelKeyListener implements KeyListener {

   int repeatCount = 0;

   public DrawingPanelKeyListener() {
     panel.addKeyListener(this);
     panel.requestFocus();
   }

   public void keyPressed(KeyEvent event) {
//     System.out.println("key pressed");
     repeatCount++;
     if ((repeatCount == 1) || (getKeysSize() < 2))
        insertKeyData(event.getKeyChar(),event.getKeyCode());
   }

   public void keyTyped(KeyEvent event) {
   }

   public void keyReleased(KeyEvent event) {
     repeatCount = 0;
   }

 }

}
