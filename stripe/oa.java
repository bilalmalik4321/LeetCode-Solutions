package stripe;
import java.util.*;

public class oa {
    
    class person {
        String name;
        List<String> banks;
        int balance;

        person(String name, List<String> banks, int balance){
            this.name = name;
            this.banks = banks;
            this.balance = balance;
        }
    }

    class StripeApp{
        Map<String, person> accounts;

        StripeApp(){
            this.accounts = new HashMap<>();
        }

        public String etransfer(){
            return "";
        }

        public String payment(){
            return "";
        }

        public String withdrawal(){
            return "";
        }
    }

}
