package lotto509.com.lotto509.models;

/**
 * Created by Carly Baja on 8/28/2017.
 */

public class Konpayel {

        private String lot1;
        private String lot2;
        private String lot3;


        public Konpayel(){

        }

        public Konpayel(String lot1, String lot2, String lot3){
            this.lot1 = lot1;
            this.lot2 = lot2;
            this.lot3 = lot3;
        }

        public String getLot1() {
            return lot1;
        }

        public void setLot1(String lot1) {
            this.lot1 = lot1;
        }

        public String getLot2() {
            return lot2;
        }

        public void setLot2(String lot2) {
            this.lot2 = lot2;
        }

        public String getLot3() {
            return lot3;
        }

        public void setLot3(String lot3) {
            this.lot3 = lot3;
        }



}
