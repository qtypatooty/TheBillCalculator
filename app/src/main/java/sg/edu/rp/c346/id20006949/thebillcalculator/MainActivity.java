package sg.edu.rp.c346.id20006949.thebillcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    TextView amount;
    EditText amountInput;
    TextView numofPax;
    EditText numofPaxInput;
    ToggleButton SVS;
    ToggleButton GST;
    TextView discount;
    EditText discountInput;
    RadioGroup rgPayment;
    Button split;
    Button reset;
    TextView totalBill;
    TextView eachPays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.textAmount);
        amountInput = findViewById(R.id.editAmount);
        numofPax = findViewById(R.id.textPax);
        numofPaxInput = findViewById(R.id.editPax);
        SVS = findViewById(R.id.toggleSVS);
        GST = findViewById(R.id.toggleGST);
        discount = findViewById(R.id.discountText);
        discountInput = findViewById(R.id.editDiscount);
        rgPayment = findViewById(R.id.radioPay);
        split = findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);
        totalBill = findViewById(R.id.totalBill);
        eachPays = findViewById(R.id.eachPays);
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pax = Integer.parseInt(numofPaxInput.getText().toString());
                double totalAmount = Double.parseDouble(amountInput.getText().toString());

                if(SVS.isChecked()){
                    totalAmount = totalAmount *1.10;
                }
                else if (GST.isChecked()){
                    totalAmount = totalAmount *1.07;
                }
                else if(SVS.isChecked() && GST.isChecked()){
                    totalAmount = totalAmount *1.17;
                }
                else{
                    totalAmount = totalAmount;
                }

                Double discountInputs = Double.parseDouble(discountInput.getText().toString());
                if(discountInputs != 0) {
                    totalAmount = (1 - (discountInputs / 100)) * totalAmount;
                }
                totalBill.setText("Total Bill: $"+ String.format("%.2f",totalAmount));
                String eachBillAmount = "Each Pays: $"+ String.format("%.2f",totalAmount/pax);

                if(rgPayment.getCheckedRadioButtonId() == R.id.radioCash){
                    eachBillAmount = eachBillAmount + " in cash";
                }
                else{
                    eachBillAmount = eachBillAmount +" via PayNow to 912345678";
                }
                eachPays.setText(eachBillAmount);


                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amountInput.setText("");
                        numofPaxInput.setText("");
                        SVS.setChecked(false);
                        GST.setChecked(false);
                        totalBill.setText("");
                        eachPays.setText("");
                        discountInput.setText("");
                    }

                });

            }
        });

    }
}


