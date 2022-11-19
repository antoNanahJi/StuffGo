package utilities;

import java.util.Iterator;
import java.util.Map;

import bean.StudentBean;
import model.SisModel;

public class View {
	
	public String studentAsHTML(SisModel model, String namePrefix, String credit_taken) {
	    String html ="";
	    Map<String, StudentBean> hmap=null;
	    try {
		hmap=model.retriveStudent(namePrefix, credit_taken);
	    }catch (Exception ex){
        	System.out.print(ex);
        }
	    
	    html="<table border=\"1\"> <thead> <td>Student Name </td> <td>Credits taken </td> <td>Credits toGraduate </td> </thead>";
	    Iterator iterator = hmap.entrySet().iterator();
	
        while (iterator.hasNext()) {
          Map.Entry me = (Map.Entry) iterator.next();
          StudentBean sb=(StudentBean)me.getValue();
          html+="<tr>"+
            "<td>"+sb.getName()+"</td>"+
        	"<td>"+sb.getCredit_taken()+"</td>"+
            "<td>"+sb.getCredit_graduate()+"</td>"+
        	"</tr>";   
         } 
	    html+="</thead></table>";
        return html;
	}

	    
	
	public String studentAsJson(SisModel model, String namePrefix, String credit_taken) {
	    String jsonString="{}";	
	  //  JsonObject value = Json.createObjectBuilder();
		Map<String, StudentBean> map=null;
	    try {
		map=model.retriveStudent(namePrefix, credit_taken);
	    }catch (Exception ex){
        	System.out.print(ex);
        }
	    if (map!=null) {
		//JsonObject value = Json.createObjectBuilder().add("", "");
	    }
		
	    jsonString="{\n" + 
	    		"  \"data\":[\n" + 
	    		"         {\"name\":\"Philipe,Rodriguez\",\"creditTaken\":81,\"creditToGraduate\":90},\n" + 
	    		"         {\"name\":\"Irene,Rodriguez\",\"creditTaken\":84,\"creditToGraduate\":90}\n" + 
	    		"     ]\n" + 
	    		"}\n";	
		
		//3.  serialize it to console..
		System.out.println(jsonString);
		
		//4. Add code to serialize it to a File..
		return jsonString;
	
	}
	
}
