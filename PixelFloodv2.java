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

public class PixelFloodv2 extends Component {


 public static void main(String[] args) {
  
  new PixelFloodv2();
 
 }
 

 private void marchThroughImage(BufferedImage image) {

  BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
while(true)
{
  try {
   //Socket clientSocket = new Socket("gazaflood.event.campzone.nl", 1234);

Socket clientSocket = new Socket();
clientSocket.connect(new InetSocketAddress("gazaflood.event.campzone.nl", 1234), 150);
//clientSocket.connect(new InetSocketAddress("145.116.217.194", 1234), 200); 


   DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
   //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  while (true)
  {
  int i = ThreadLocalRandom.current().nextInt(1, image.getWidth());
  int j = ThreadLocalRandom.current().nextInt(1, image.getHeight());
  //System.out.println(Integer.toString(j) + " " + Integer.toString(i));
  int pixel = image.getRGB(i, j);
  String newLine = "PX " + Integer.toString(i+1180) + " " + Integer.toString(j+380) + " " + printPixelRGB(pixel) + "\n";
  //String newLine = "PX " + Integer.toString(i) + " " + Integer.toString(j) + " " + printPixelRGB(pixel) + "\n";
  //System.out.println(newLine);
   outToServer.writeBytes(newLine);
  }
   //clientSocket.close();
  } catch (UnknownHostException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  } catch (Exception e) {
   e.printStackTrace();
  }
  }


}

public PixelFloodv2() {

 try {
  // get the BufferedImage, using the ImageIO class
  String file = "buurman.jpg";
  BufferedImage image =
   ImageIO.read(this.getClass().getResource(file));
  marchThroughImage(image);
 } catch (IOException e) {
  System.err.println(e.getMessage());
 }
}

public String printPixelRGB(int pixel) {
  //int alpha = (pixel >> 24) & 0xff;
  int red = (pixel >> 16) & 0xff;
  int green = (pixel >> 8) & 0xff;
  int blue = (pixel) & 0xff;
  return Integer.toHexString(red).toUpperCase() + Integer.toHexString(green).toUpperCase() + Integer.toHexString(blue).toUpperCase();
 }


}