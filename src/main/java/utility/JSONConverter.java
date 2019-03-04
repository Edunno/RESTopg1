/* Esben Dalgaard; DECK-CS */
package utility;

import static com.mysql.cj.MysqlType.JSON;
import entity.Person;
import java.util.List;
import org.json.JSONObject;

/**
 * @author Esben All rights belong to respective contributors.
 */
public class JSONConverter {

    public  Person getPersonFromJson(String js) {
        JSONObject jsObj = new JSONObject(js);
        Person p = new Person();
        p.setFirstName((String)jsObj.get("fName"));
        p.setLastName((String)jsObj.get("lName"));
        p.setPhone((String)jsObj.get("phone"));
        return p;
    }
    public String getJsonFromPerson(Person p) {
        return "{\"fName\":\""+p.getFirstName()+"\",\"lName\":\""+p.getLastName()+"\",\"phone\":\""+p.getPhone()+"\",\"id\":"+p.getId()+"}";
    }
    public String getJsonFromPersons(List<Person> persons){
        String res = "[\n";
        for(int i = 0; i < persons.size();i++){
            res += "{\"fName\":\""+persons.get(i).getFirstName()+"\",\"lName\":\""+persons.get(i).getLastName()+"\",\"phone\":\""+persons.get(i).getPhone()+"\",\"id\":"+persons.get(i).getId()+"}\n";
        }
        res += "]";
        return res;
    }
}
