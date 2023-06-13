package vn.shop.convert;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.xml.bind.DatatypeConverter;

import javax.imageio.ImageIO;


@Service
public class ConvertImage {

    public String encodeImage(MultipartFile file,String type) throws Exception {

        // read image from file

        InputStream stream=  new BufferedInputStream(file.getInputStream());


        BufferedImage image = ImageIO.read(stream);


        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ImageIO.write(image, type, out);
        byte[] imageBytes = out.toByteArray();

        String imageString = DatatypeConverter.printBase64Binary(imageBytes);

//        out.close();
//
//        String namefile=saveFile(file.getOriginalFilename(),imageString);

        return imageString;
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


    public static String  readFiles(String url) {
        // Đọc dữ liệu từ File với BufferedReader
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = bufferedReader.readLine();
            return line;
        } catch (FileNotFoundException ex) {} catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "null";
    }


    private static String saveFile(String txt,String base64) {
        //tạo foder
        File newDir = new File("/Users/t.u.n.ggg/Documents/DOAN/quanlybanhang/src/main/resources/static/imageBase64");
        if (newDir.mkdir()) {
            System.out.println("Directory created");
        } else {
            System.out.println("Directory not created");
        }
        //tạo tên file
        String[] arrImg =  txt.split("\\.");
        String duoiFileImg = arrImg[arrImg.length - 1];//lấy đuôi file hiện tại
        String duoiFileTxt="txt";
        String nameFile = "";
        for (int i  = 0;i< (arrImg.length - 1) ; i++) {
            if(i == 0){
                nameFile = arrImg[i];
            }else{
                nameFile += "-"+arrImg[i];
            }
        }
        double randomDouble = Math.random();
        randomDouble = randomDouble * 999 + 1;
        int randomInt = (int) randomDouble;
        nameFile = newDir+"/"+nameFile + "-"+System.nanoTime()+"-"+randomInt +"."+duoiFileTxt;

        //lưu file
        FileOutputStream file = null;
        byte[] baser64Byte = base64.getBytes(); //ep kieu String ve mang byte
        try{
            file = new FileOutputStream(nameFile);
            file.write(baser64Byte); //ghi mang byte vao file
            System.out.print("Da ghi thanh cong!");
            return nameFile;
        }
        catch (Exception e){
            System.out.print(e);// In lỗi ra màn hình
        }
        return null;
    }


}
