package br.com.rodrigo.OFP.infra;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile file) {
		try {
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));
			BufferedImage originalImage = ImageIO.read(new File(path));
			BufferedImage thumbnail = Scalr.resize(originalImage, 150);
			
            ImageIO.write(thumbnail, "png", new File(path));
			return  file.getOriginalFilename();

		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}


}
