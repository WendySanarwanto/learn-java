package me.wendysa.jspcrud;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PriceTag extends SimpleTagSupport {
  public void doTag() throws JspException, IOException {
    StringWriter stringWriter = new StringWriter();
    
    // Get content from JSP Body
    getJspBody().invoke(stringWriter);
    String content = stringWriter.toString();

    // Parse the content into POJO instance
    ObjectMapper mapper = new ObjectMapper();    
    Price price = mapper.readValue(content, Price.class);

    // Output the POJO Instance
    JspWriter out = getJspContext().getOut();
    out.println(price.toString());
  }
};
