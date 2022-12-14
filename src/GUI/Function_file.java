package GUI;

import java.awt.*;
import java.io.*;

public class Function_file {
   GUI gui;
    public  String fileName,fileAddress;


    public Function_file(GUI gui){
        this.gui=gui;

    }
    public void newFile(){
        gui.jTextArea.setText("");
        gui.windown.setTitle("New");
        fileName=null;
        fileAddress=null;
    }

    public void openFile(){
        FileDialog fileDialog=new FileDialog(gui.windown,"Open",FileDialog.LOAD);
        fileDialog.setVisible(true);
        if (fileDialog.getFile()!=null){
            fileName=fileDialog.getFile();
            fileAddress=fileDialog.getDirectory();
            gui.windown.setTitle(fileName.substring(0, fileName.lastIndexOf('.')));

        }
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileAddress + fileName));
            gui.jTextArea.setText("");
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                gui.jTextArea.append(line + "\n");
            }
            bufferedReader.close();

        }catch (IOException e){
            System.out.println("FILE NOT OPEND!");

        }

    }
    public void saveFile(){
        if(fileName==null){
            saveAs();
        }
        else {
            try{
                FileWriter fileWriter=new FileWriter(fileAddress + fileName);
                fileWriter.write(gui.jTextArea.getText());
                gui.windown.setTitle(fileName.substring(0, fileName.lastIndexOf('.')));
                fileWriter.close();

            }catch (IOException e){

                System.out.println("SOMETHING WORONG");
            }
        }


    }

    public void saveAs(){
        FileDialog fileDialog=new FileDialog(gui.windown,"Save",FileDialog.SAVE);
        fileDialog.setFilenameFilter(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java") || name.endsWith(".cpp")||name.endsWith(".py")||name.endsWith(".js");
            }
        });
        fileDialog.setFile("Untitled");
        fileDialog.setVisible(true);



        if (fileDialog.getFile()!=null){
            fileName=fileDialog.getFile();
            fileAddress=fileDialog.getDirectory();
            gui.windown.setTitle(fileName.substring(0, fileName.lastIndexOf('.')));

        }
        try {
            FileWriter fileWriter=new FileWriter(fileAddress + fileName);
            fileWriter.write(gui.jTextArea.getText());
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("SOMETHING WORONG");

        }
    }
    public String getFilename(){
        return fileName;
    }
    public void exit(){
        System.exit(0);
    }

}
