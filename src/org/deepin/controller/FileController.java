package org.deepin.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.codec.binary.Base64;
import org.deepin.service.IFileService;
import org.deepin.util.RestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

@Controller
public class FileController {
	  @Autowired
	  private IFileService fileService;
	  /**
	   * http://sharebo.oss-cn-shanghai.aliyuncs.com/image/58a0ad71-391e-496c-b86b-871e0d791f73
	   * @return
	   * @throws IOException
	   * @throws ParserConfigurationException
	   * @throws SAXException
	   */
	  @RequestMapping("upload")
	  public @ResponseBody Map<String, Object> fileUpload() throws IOException, ParserConfigurationException, SAXException{
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		File fileisE = new File("D:\\Android\\1.jpg"); 
		FileInputStream inputFile = new FileInputStream(fileisE);
		byte[] buffer = new byte[(int)fileisE.length()];
	    inputFile.read(buffer);
	    inputFile.close();
	    String file = new Base64().encodeToString(buffer);
	    byte[] oc = new Base64().decode(file);
		ByteArrayInputStream org = new ByteArrayInputStream(oc);
		//写入到指定的目录
		String newFileName =this.fileService.uploadImage(org);
		System.out.println(newFileName);
		mapRtn.put(RestDto.RESULT,newFileName);
		return mapRtn;
	}
}
