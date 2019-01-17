/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw_function;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author walide
 */
public class functions {

    //foction pour rexhercher le x dans un string___________________
    public String cherche_x(String exp, double value) {
        for (int pos = 0; pos <= exp.length(); pos++) {
            if (pos == exp.indexOf("x")) {
                if (pos == 0) {
                    exp = value + exp.substring(pos + 1, exp.length());
                } else if (exp.charAt(pos - 1) == '*' || exp.charAt(pos - 1) == '/' || exp.charAt(pos - 1) == '+' || exp.charAt(pos - 1) == '-' || exp.charAt(pos - 1) == '(') {
                    exp = exp.substring(0, pos) + value + exp.substring(pos + 1, exp.length());
                } else {
                    exp = exp.substring(0, pos) + "*" + value + exp.substring(pos + 1, exp.length());
                }
            }
        }
        return exp;
    }

    // fonction pour power _____________________________
    public String power(String s){
        int p_pos,pos_part1=0,pos_part2=0;
        String part_pwr;
        String part1,part2;
        for(int i=0;i<s.length();i++){
          if(s.charAt(i)=='^'){ 
              if(s.charAt(i-1)==')'){
              System.out.println("i="+i);
               p_pos=parenthese_inv(s,i);
               part1=s.substring(p_pos, i);
               System.out.println("part1="+part1);
              }else{
                   System.out.println("pos="+i);
                  pos_part1=cherche_char_inv(s,"*+-/",i);
                  part1=s.substring(pos_part1+1, i);
                  System.out.println("part1="+part1);
              }
              if(s.charAt(i+1)=='('){
                  p_pos=parenthse(s,i);
                  part2=s.substring(i, p_pos);
                  System.out.println("part2="+part2);
              }else{
                 pos_part2=multiCharAt(s, "*+-/)",i);
                 part2=s.substring(i+1, pos_part2);
                 System.out.println("part2="+part2);
              }
              part_pwr=part1+"^"+part2;
              System.out.println("spart_pwr="+part_pwr);
              s=s.replace(part_pwr, "Math.pow("+part1+","+part2+")");
               System.out.println("s="+s);

          }  
        }
        System.out.println("s="+s);
        return s;
    }
    public String correct_string(String s){
        s=s.toLowerCase();
        s=power(s);
        s=s.replace("sin", "Math.sin");
        s=s.replace("cos", "Math.cos");
        s=s.replace("tan", "Math.tan");
        s=s.replace("pi", "Math.PI");
        s=s.replace("abs", "Math.abs");
        s=s.replace("round", "Math.round");
        s=s.replace("ceil", "Math.ceil");
        s=s.replace("floor", "Math.floor");
        s=s.replace("sqrt", "Math.sqrt");
        s=s.replace("log", "Math.log");
        s=s.replace("ln", "Math.ln");
        s=s.replace("exp", "Math.exp");
        s=s.replace("min", "Math.min");
        s=s.replace("max", "Math.max");
        s=s.replace("acos", "Math.acos");
        s=s.replace("asin", "Math.asin");
        s=s.replace("atan", "Math.atan");
        
        return s;
    }
  public int parenthse(String s,int pos){
      int compt=0;
      for(int i=0;i<s.length();i++){
        if(s.charAt(i)=='('){
            compt+=1;
        }  
        if(s.charAt(i)==')'){
            compt-=1;
            if(compt==0){
              return i;  
            }   
        }
        
      }
      if(compt!=0){
            System.out.println("virifier votre parethese SVP!!");
        }
    return pos;  
  }
public int parenthese_inv(String s,int pos){
  int compt=0;
      while(pos>=0){
        if(s.charAt(pos)=='('){
            compt-=1;
            if(compt==0){
                return pos;
            }
        }  
        if(s.charAt(pos)==')'){
            compt+=1;
        }
        pos--;
      }
      if(compt!=0){
            System.out.println("virifier votre parethese SVP!!"+compt);
        }
           System.out.println(pos);
      return pos;
}  

    public boolean is_here(String s, char c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c ) {
                return true;
            }
        }
        return false;
    }

    public double exprition(String exp) throws ScriptException {
        ScriptEngineManager m = new ScriptEngineManager();
        ScriptEngine e = m.getEngineByName("js");
        Object r = e.eval(exp);
        return Double.parseDouble(String.valueOf(r));
    }

    public int multiCharAt(String s, String s2,int start) {
        int pos = s.length();
        System.out.println("la start est ="+start);
        for (int i = 0; i < s2.length(); i++) {
            for(int j=start;j<s.length();j++){
                if(s.charAt(j)==s2.charAt(i)){
                    System.out.println("la position est ="+j);
                    return j;
                }
            }
            
        }
        return pos;
    }
    public int cherche_char_inv(String s, String s2,int pos){
int i=pos;
while(i>=0){
    for(int j=0;j<s2.length();j++){
      if(s2.charAt(j)==s.charAt(i)){
          return i;
      }  
    }
    i--;
}
             
        return i;
    }
    

    public static void main(String[] args) throws ScriptException {
        functions k = new functions();
        System.out.println(k.cherche_x("2x+17/x-3", 4));
        String s="2x^5+2^2+12";
        //System.out.println(k.parenthese_inv("(2x+12)*(12X+3)",14));
        k.power("(2x+1*2^1)^2+8x+5x^3");
        //System.out.println(k.power(""));
//Math.pow((2x+1*Math.pow(2,1),2)+8x+Math.pow(5x,3)
//Math.pow((2x+1*Math.pow(2,1)),2)+8x+Math.pow(5x,3)
    }

}
