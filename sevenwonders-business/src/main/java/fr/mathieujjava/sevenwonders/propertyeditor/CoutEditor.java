package fr.mathieujjava.sevenwonders.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import fr.mathieujjava.sevenwonders.Cout;
import fr.mathieujjava.sevenwonders.enums.Ressource;

public class CoutEditor extends PropertyEditorSupport {
  public void setAsText(String text) throws IllegalArgumentException {
   
   String[] sCouts = text.split(",");
   Cout[] couts = new Cout[sCouts.length];
   System.out.println(text + " " + sCouts[0] + " " + sCouts[1] + " " + sCouts[2] + " " + sCouts.length);
   int i = 0;
    for (String sCout : sCouts) {
      Cout cout = new Cout(0);
      for (char c : sCout.toCharArray()) {
        switch (c) {
        case 'B':
          cout.ajoute(Ressource.Bois);
          break;
        case 'P':
          cout.ajoute(Ressource.Pierre);
          break;
        case 'Q':
          cout.ajoute(Ressource.Brique);
          break;
        case 'M':
          cout.ajoute(Ressource.Minerai);
          break;
        case 'T':
          cout.ajoute(Ressource.Tissu);
          break;
        case 'V':
          cout.ajoute(Ressource.Verre);
          break;
        case 'Y':
          cout.ajoute(Ressource.Papyrus);
          break;
        }
        
      }
      System.out.println(" " + i + " " + cout);
      couts[i] = cout;
      i++;
    }
    setValue(couts);
  }
}
