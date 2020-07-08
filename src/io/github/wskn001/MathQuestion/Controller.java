/*
 *     MathQuestionHelper
 *     Copyright (c) 2020 wangshengkai
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *     Contact me : Wechat: vic20070927 Phone: 18514463331
 *
 *     数学题助手
 *     版权所有（C）2020 wangshengkai
 *     本程序为自由软件，在自由软件联盟发布的GNU通用公共许可协议的约束下，你可以对其进行再发布及修改。协议版本为第三版或（随你）更新的版本。
 *     我们希望发布的这款程序有用，但不保证，甚至不保证它有经济价值和适合特定用途。详情参见GNU通用公共许可协议。
 *     你理当已收到一份GNU通用公共许可协议的副本，如果没有，请查阅<http://www.gnu.org/licenses/>
 *
 *     联系方式： 微信：vic20070927 电话：18514463331
 */

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
        textConsole.setText("");
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
        run(".\\jre7\\bin\\javac.exe Main.java");
        run(".\\jre7\\bin\\java.exe Main");
    }


    public void run(String command){
        Runtime run = Runtime.getRuntime();
        try {
            textConsole.setText(textConsole.getText() + command + "\n");
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
                    line.append(new String(b));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            textConsole.setText(textConsole.getText() + line.toString() + "\n");
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
