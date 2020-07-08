package io.github.wskn001.MathQuestion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class Controller {
    @FXML
    private Button btnRun;
    @FXML
    private TextArea textImport;
    @FXML
    private TextArea textMainCode;
    @FXML
    private TextArea textOtherCode;
    @FXML
    private TextArea textConsole;
    @FXML
    public void btnRunClicked(MouseEvent mouseEvent) {
        StringBuilder sb = new StringBuilder();
        sb.append(textImport.getText());
        sb.append(
            "public class Main{\n"
                + "\tpublic static void main(String[] args) {\n"
                + "\t\tnew Main().go();\n"
                + "\t}");
        sb.append("public void go(){");
        sb.append(textMainCode.getText());
        sb.append("}");
        sb.append(textOtherCode.getText());
        sb.append("}");
        writeFile("Main.java" , sb.toString());
        run(".\\jre\\bin\\java.exe Main.java");
    }


    public void run(String command){
        Runtime run =Runtime.getRuntime();
        try {
            Process p = run.exec(command);
            InputStream ins= p.getInputStream();
            InputStream ers= p.getErrorStream();
            new Thread(new inputStreamThread(ins)).start();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    class inputStreamThread implements Runnable{
        private InputStream ins = null;
        private BufferedReader bfr = null;
        public inputStreamThread(InputStream ins){
            this.ins = ins;
            this.bfr = new BufferedReader(new InputStreamReader(ins));
        }
        @Override
        public void run() {
            StringBuilder line = new StringBuilder();
            byte[] b = new byte[100];
            int num = 0;
            try {
                while((num=ins.read(b))!=-1){
                    line.append(b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            textConsole.setText(line.toString());
        }

    }

    public void writeFile(String path , String content){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            out.write(content);
            out.close();
            System.out.println("文件写入成功！");
        } catch (IOException e) {
        }
    }
}
