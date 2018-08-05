import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.net.*;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.ThreadLocalRandom;

public class PixelFloodv3 extends Component {
 public static void main(String[] args) { 
  new PixelFloodv3();
 }
 
 private void marchThroughImage(BufferedImage image) {
  BufferedReader inFromUser =
  new BufferedReader(new InputStreamReader(System.in));


  //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
  while(true)
  {
   try {
    DatagramSocket clientSocket = new DatagramSocket();
    InetAddress IPAddress = InetAddress.getByName("gazaflood.event.campzone.nl");
    byte[] sendData = new byte[7]; 
    while (true)
    {
     int i = ThreadLocalRandom.current().nextInt(1, image.getWidth());
     int j = ThreadLocalRandom.current().nextInt(1, image.getHeight());
     int pixel = image.getRGB(i, j);
    i=i+1180;
    j=j+380;
    sendData[0] = (byte) (i >>> 8);
    sendData[1] = (byte) i;
    sendData[2] = (byte) (j >>> 8);
    sendData[3] = (byte) j;
    sendData[4] = (byte) ((pixel >> 16) & 0xff);
    sendData[5] = (byte) ((pixel >> 8) & 0xff);
    sendData[6] = (byte) ((pixel) & 0xff);
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1235);
    clientSocket.send(sendPacket);
    }
  }
  catch (UnknownHostException e) {
   e.printStackTrace();} 
  catch (SocketException e) {
   e.printStackTrace();} 
  catch (Exception e) {
  e.printStackTrace();}
 }
}

public PixelFloodv3() {
 try {
  // get the BufferedImage, using the ImageIO class
  String file = "buurman.jpg";
  BufferedImage image =
   ImageIO.read(this.getClass().getResource(file));
  marchThroughImage(image);
   } 
   catch (IOException e) {
   System.err.println(e.getMessage());
   }
 }
}