import java.awt.Image;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;

import javax.security.auth.login.AppConfigurationEntry;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class peice extends JLabel{
    public int x,y,w,h;
    public boolean damma=false;
    String color;
    peice(int i,int j,int w,int h){
        this.x=i;
        this.y=j;
        this.w=w;
        this.h=h;
        String s=new String();
        s=((j/checkers.cell_side)<5)?"black":"white";
        this.color=s;
        if (((i/checkers.cell_side)%2==0 && (j/checkers.cell_side)%2!=0 || (i/checkers.cell_side)%2!=0 && (j/checkers.cell_side)%2==0) && (j<3+checkers.cell_side || j>4+checkers.cell_side) ){
            ImageIcon img=new ImageIcon(new ImageIcon(s+".png").getImage().getScaledInstance(70, 70,Image.SCALE_SMOOTH));
            setIcon(img);
            setVisible(true);
            this.setBounds(i,j,w,h);
            setVisible(true);

        }

    }


    public void move(int g,int d){ 
        this.x=g;this.y=d;
        this.setBounds(g,d,h,w);
    }


    public static int[] apparray(int[]arr,int new1,int new2){
        if (arr==null){
            arr=new int[0];
            System.out.println("initialised");
        }

        for (int i=2;i<arr.length;i+=3){
            if( arr[i]==new2&&arr[i-1]==new1){
                return arr;
            }
        }
        
        int[] newarr=new int[arr.length+3];
        for (int i=0;i<arr.length;i++){
            newarr[i]=arr[i];
        }
        boolean ok=true; 
        for (int i=0;i<arr.length;i+=2){
            if (arr[i]==new1 &&arr[i+1]==new2){
                ok=false;
            }
        }if(ok){
            newarr[arr.length+1]=new1;
            newarr[arr.length+2]=new2;
            return newarr;
        }else{return arr;}
    }


    //attempt 6 
    public static int[] droitt(int x,int y, int[] pm){ 
        if (pm!=null){pm=apparray(pm, x, y);}
        boolean a1=true,a2=true;
        for (int i=0;i<12;i++){
            if (x+checkers.cell_side<checkers.width&&y+checkers.cell_side<checkers.width&&(checkers.white[i].x==x+checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side)){
                a1=false;break;
            }
            if(x+checkers.cell_side<checkers.width&&y+checkers.cell_side<checkers.width&&(checkers.black[i].x==x+checkers.cell_side&&checkers.black[i].y==y+checkers.cell_side)){
                a2=false;break;
            }
        }
        if(!a1){return pm;}
        if(!a2){
            for (int i=0;i<12;i++){
                if (x+checkers.cell_side*2<checkers.width&&y+checkers.cell_side*2<checkers.width&&(checkers.white[i].x==x+checkers.cell_side*2&&checkers.white[i].y==y+checkers.cell_side*2||checkers.black[i].x==x+checkers.cell_side*2&&checkers.black[i].y==y+checkers.cell_side*2)){
                    return pm;
                }
            }
            pm=apparray(pm, x+checkers.cell_side*2, y+checkers.cell_side*2);
            return middle(x+checkers.cell_side*2,y+checkers.cell_side*2,pm);
        }
        return pm;
    }

    
    public static int[] gauche(int x,int y, int[] pm){ 
        if (pm!=null){pm=apparray(pm, x, y);}
        boolean a1=true,a2=true;
        for (int i=0;i<12;i++){
            if (x-checkers.cell_side>=0&&y+checkers.cell_side<checkers.width&&(checkers.white[i].x==x-checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side)){
                a1=false;break;
            }
            if(x-checkers.cell_side>=0&&y+checkers.cell_side<checkers.width&&(checkers.black[i].x==x-checkers.cell_side&&checkers.black[i].y==y+checkers.cell_side)){
                a2=false;break;
            }
        }
        if(!a1){return pm;}
        if(!a2){
            for (int i=0;i<12;i++){
                if (x-checkers.cell_side*2>=0&&y+checkers.cell_side*2<checkers.width&&(checkers.white[i].x==x-checkers.cell_side*2&&checkers.white[i].y==y+checkers.cell_side*2||checkers.black[i].x==x-checkers.cell_side*2&&checkers.black[i].y==y+checkers.cell_side*2)){
                    return pm;
                }
            }
            pm=apparray(pm, x-checkers.cell_side*2, y+checkers.cell_side*2);
            return middle(x-checkers.cell_side*2,y+checkers.cell_side*2,pm);
        }
        return pm;
    }


    public static int[] middle(int x,int y,int[] pm){
        int[] C,V,res=new int[0];
        C=droitt(x, y, pm); 
        V=gauche(x, y, pm);
        System.out.println(Arrays.toString(C)+" printed c");
        System.out.println(Arrays.toString(V)+" printed v");
        int lenth=0;
        if(C !=null){lenth+=C.length;}
        if(V !=null){lenth+=V.length;}
        for (int i=1;i<lenth;i+=3){
            if(V!=null&&i<V.length){
                res=apparray(res,V[i],V[i+1]);System.out.println("appended v");
            }
            if(C!=null&&i<C.length){
                res=apparray(res,C[i],C[i+1]);System.out.println("appended C");
            }
        }
        return res;
    }

    //attempt 5 inhabitable 
    public static int[] possibleee(int x,int y,int[]pm){ 
        System.out.println(Arrays.toString(pm));
        boolean a1=true,a2=true;
        for (int i=0;i<12;i++){
            if (x+checkers.cell_side<526&&y+checkers.cell_side<526&&(checkers.white[i].x==x+checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side||checkers.black[i].x==x+checkers.cell_side&&checkers.black[i].y==y+checkers.cell_side)){
                a1=false;break;
            }
            if (x-checkers.cell_side>=0&&y+checkers.cell_side<526&&(checkers.white[i].x==x-checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side||checkers.black[i].x==x-checkers.cell_side&&checkers.black[i].y==y+checkers.cell_side)){
                a2=false;break;
            }
        }
        if (pm !=null){
            if (a1&&!a2){
               
                boolean a=true;
                for (int i=0;i<12;i++){
                    if (x-150>=0&&y+150<526&&(checkers.black[i].x==x-150&&checkers.black[i].y==y+150)){
                        a=false;break;
                    }
                    if (checkers.white[i].x==x-checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side){return pm;}
                }
                
                if(a&&x-150>=0&&y+150<526){
                    pm=apparray(pm,x-150,y+150);
                    return possibleee(x-150, y+150, pm);
                }else{
                    return pm;
                }


            }else if (!a1&&a2){
                boolean a=true;
                for (int i=0;i<12;i++){
                    if (x+150<526&&y+150<526&&(checkers.black[i].x==x+150&&checkers.black[i].y==y+150)){
                        a=false;break;
                    }
                    if (checkers.white[i].x==x+checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side){return pm;}
                }
                
                if(a&&x+150<526&&y+150<526){
                    pm=apparray(pm,x+150,y+150);
                    return possibleee(x+150, y+150, pm);
                }else{
                    return pm;
                }
            }else{return pm;}
        }
        if (a1&&a2){
            if (x+checkers.cell_side<526&&y+checkers.cell_side<526&&x-checkers.cell_side>=0){
                pm=apparray(pm,x+checkers.cell_side,y+checkers.cell_side);
                pm=apparray(pm,x-checkers.cell_side,y+checkers.cell_side);
            }
            return pm;
        }else if(a1&&!a2){
            if(x+checkers.cell_side<526&&y+checkers.cell_side<526){
                pm=apparray(pm,x+checkers.cell_side,y+checkers.cell_side);
            }
            boolean a=true;
            for (int i=0;i<12;i++){
                if (x-150>=0&&y+150<526&&(checkers.black[i].x==x-150&&checkers.black[i].y==y+150)){
                    a=false;break;
                }
                if (checkers.white[i].x==x-checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side){return pm;}
            }
            if (a&&x-150>=0&&y+150<526){
                pm=apparray(pm, x-150, y+150);
                return possibleee(x-150,y+150,pm);   
            }else {
                return pm;
            }
        }else if(!a1&&a2){
            if (x-checkers.cell_side>=0&&y+checkers.cell_side<526){pm=apparray(pm,x-checkers.cell_side,y+checkers.cell_side);}
            boolean a=true;
            for (int i=0;i<12;i++){
                if (x+150<526&&y+150<526&&(checkers.black[i].x==x+150&&checkers.black[i].y==y+150)){
                    a=false;break;
                }
                if (checkers.white[i].x==x+checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side){return pm;}
            }
            if(a&&x+150<526&&y+150<526){ 
                pm=apparray(pm, x+150, y+150);
                return possibleee(x+150,y+150,pm);
            }else{return pm;}

        }else if(!a1&&!a2){
            boolean a3=true,a4=true;
            for (int i=0;i<12;i++){
                if (x+150<526&&y+150<526&&(checkers.white[i].x==x+150&&checkers.white[i].y==y+150||checkers.black[i].x==x+150&&checkers.black[i].y==y+150)){
                    a3=false;break;
                }
                if (x-150>=0&&y+150<526&&(checkers.white[i].x==x-150&&checkers.white[i].y==y+150||checkers.black[i].x==x-150&&checkers.black[i].y==y+150)){
                    a4=false;break;
                }
                if (checkers.white[i].x==x-checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side){return pm;}
                if (checkers.white[i].x==x+checkers.cell_side&&checkers.white[i].y==y+checkers.cell_side){return pm;}
            }
            if(a3){
                pm=apparray(pm, x+150, y+150);
                return possibleee(x+150,y+150, pm);
            }
            if(a4){ 
                pm=apparray(pm, x-150, y+150);
                return possibleee(x-150,y+150,pm);
            }
        }
        return null;
    }


    public static int[] possibleblack(int x,int y,int[]pm){ 
        System.out.println(Arrays.toString(pm));
        boolean a1=true,a2=true;
        for (int i=0;i<12;i++){
            if (x+checkers.cell_side<526&&y-checkers.cell_side>=0&&(checkers.white[i].x==x+checkers.cell_side&&checkers.white[i].y==y-checkers.cell_side||checkers.black[i].x==x+checkers.cell_side&&checkers.black[i].y==y-checkers.cell_side)){
                a1=false;
            }
            if (x-checkers.cell_side>=0&&y-checkers.cell_side>=0&&(checkers.white[i].x==x-checkers.cell_side&&checkers.white[i].y==y-checkers.cell_side||checkers.black[i].x==x-checkers.cell_side&&checkers.black[i].y==y-checkers.cell_side)){
                a2=false;
            }
        }
        if (pm !=null){
            if (!a1&&!a2){
                return pm;
            }else if (a1&&!a2){
               
                boolean a=true;
                for (int i=0;i<12;i++){
                    if (x-150>=0&&y-150>=0&&(checkers.white[i].x==x-150&&checkers.white[i].y==y-150)){
                        a=false;
                    }
                    if (checkers.black[i].x==x-checkers.cell_side&&checkers.black[i].y==y-checkers.cell_side){return pm;}
                }
                
                if(a&&x-150>=0&&y-150>=0){
                    pm=apparray(pm,x-150,y-150);
                    return possibleee(x-150, y-150, pm);
                }else{
                    return pm;
                }


            }else if (!a1&&a2){
                boolean a=true;
                for (int i=0;i<12;i++){
                    if (x+150<526&&y-150>=0&&(checkers.white[i].x==x+150&&checkers.white[i].y==y-150)){
                        a=false;
                    }
                    if (checkers.black[i].x==x+checkers.cell_side&&checkers.black[i].y==y-checkers.cell_side){return pm;}
                }
                
                if(a&&x+150<526&&y-150>=0){
                    pm=apparray(pm,x+150,y-150);
                    return possibleee(x+150, y-150, pm);
                }else{
                    return pm;
                }
            }else{return pm;}
        }
        if (a1&&a2){
            if (x+checkers.cell_side<526&&y-checkers.cell_side>=0&&x-checkers.cell_side>=0){
                pm=apparray(pm,x+checkers.cell_side,y-checkers.cell_side);
                pm=apparray(pm,x-checkers.cell_side,y-checkers.cell_side);
            }
            return pm;
        }else if(a1&&!a2){
            if(x+checkers.cell_side<526&&y-checkers.cell_side>=0){
                pm=apparray(pm,x+checkers.cell_side,y-checkers.cell_side);
            }
            boolean a=true;
            for (int i=0;i<12;i++){
                if (x-150>=0&&y-150>=0&&(checkers.white[i].x==x-150&&checkers.white[i].y==y-150)){
                    a=false;
                }
                if (checkers.black[i].x==x-checkers.cell_side&&checkers.black[i].y==y-checkers.cell_side){return pm;}
            }
            if (a&&x-150>=0&&y-150>=0){
                pm=apparray(pm, x-150, y-150);
                return possibleee(x-150,y-150,pm);   
            }else {
                return pm;
            }
        }else if(!a1&&a2){
            if (x-checkers.cell_side>=0&&y-checkers.cell_side>=0){pm=apparray(pm,x-checkers.cell_side,y-checkers.cell_side);}
            boolean a=true;
            for (int i=0;i<12;i++){
                if (x+150<526&&y-150>=0&&(checkers.white[i].x==x+150&&checkers.white[i].y==y-150)){
                    a=false;
                }
                if (checkers.black[i].x==x+checkers.cell_side&&checkers.black[i].y==y-checkers.cell_side){return pm;}
            }
            if(a&&x+150<526&&y-150>=0){ 
                pm=apparray(pm, x+150, y-150);
                return possibleee(x+150,y-150,pm);
            }else{return pm;}

        }else if(!a1&&!a2){
            boolean a3=true,a4=true,a5=true,a6=true;
            for (int i=0;i<12;i++){
                if (x+150<526&&y-150>=0&&(checkers.white[i].x==x+150&&checkers.white[i].y==y-150)){
                    a3=false;
                }
                if (x-150>=0&&y-150>=0&&(checkers.white[i].x==x-150&&checkers.white[i].y==y-150)){
                    a4=false;
                }
                if (checkers.black[i].x==x+checkers.cell_side&&checkers.black[i].y==y-checkers.cell_side){a5=false;}
                if (checkers.black[i].x==x-checkers.cell_side&&checkers.black[i].y==y-checkers.cell_side){a6=false;}
            }
            if (!a5&&!a6){return pm;}
            if(a3&&a5){
                pm=apparray(pm, x+150, y-150);
                return possibleee(x+150,y-150, pm);
            }
            if(a4&&a6){ 
                pm=apparray(pm, x-150, y-150);
                return possibleee(x-150,y-150,pm);
            }
        }
        return null;
    }






    // public static int[] possible_moves(peice p){
    //     if (!p.damma){
    //         if(p.color=="black"){

    //             boolean a1=true,a2=true;
    //             for (int i=0;i<12;i++){
    //                 if (p.x+checkers.cell_side<526&&p.y+checkers.cell_side<526&&(checkers.white[i].x==p.x+checkers.cell_side&&checkers.white[i].y==p.y+checkers.cell_side||checkers.black[i].x==p.x+checkers.cell_side&&checkers.black[i].y==p.y+checkers.cell_side)){
    //                     a1=false;
    //                 }
    //                 if (p.x-checkers.cell_side>=0&&p.y+checkers.cell_side<526&&(checkers.white[i].x==p.x-checkers.cell_side&&checkers.white[i].y==p.y+checkers.cell_side||checkers.black[i].x==p.x-checkers.cell_side&&checkers.black[i].y==p.y+checkers.cell_side)){
    //                     a2=false;
    //                 }
    //             }
    //             if (a1&&!a2){
                    
    //                 boolean a=true;
    //                 for (int i=0;i<12;i++){
    //                     if (p.x-150>=0&&p.y+150<526&&(checkers.white[i].x==p.x-150&&checkers.white[i].y==p.y+150||checkers.black[i].x==p.x-150&&checkers.black[i].y==p.y+150)){
    //                         a=false;
    //                     }

    //                 }
    //                 if (a){ 
    //                     boolean a3=true,a4=true;
    //                     for (int i=0;i<12;i++){
    //                         if (p.x+225<526&&p.y+225<526&&(checkers.white[i].x==p.x+225&&checkers.white[i].y==p.y+225||checkers.black[i].x==p.x+225&&checkers.black[i].y==p.y+225)){
    //                             a3=false;
    //                         }
    //                         if (p.x+checkers.cell_side>=0&&p.y+225<526&&(checkers.white[i].x==p.x+checkers.cell_side&&checkers.white[i].y==p.y+225||checkers.black[i].x==p.x+checkers.cell_side&&checkers.black[i].y==p.y+225)){
    //                             a4=false;
    //                         }
    //                     }
    //                     if (a3&&!a4){
    //                         int[] arr={p.x+checkers.cell_side,p.y+checkers.cell_side,p.x-150,p.y+150,p.x+225,p.y+225};
    //                         return arr;   
    //                     }else if(a4&&!a3){
    //                         int[]arr={p.x+checkers.cell_side,p.y+checkers.cell_side,p.x-150,p.y+150,p.x+checkers.cell_side,p.y+225};
    //                         return arr;
    //                     }else if(a4&&a3){ 
    //                         int[]arr={p.x+checkers.cell_side,p.y+checkers.cell_side,p.x-150,p.y+150,p.x+225,p.y+225,p.x+checkers.cell_side,p.y+225};
    //                         return arr;
    //                     }else {int[]arr={p.x+checkers.cell_side,p.y+checkers.cell_side,p.x-150,p.y+150};return arr;}

    //                 }else {
    //                     int[]arr={p.x+checkers.cell_side,p.y+checkers.cell_side}; return arr;
    //                 }
                    
    //             }else if (!a1&&a2){
    //                 boolean a=true;
    //                 for (int i=0;i<12;i++){
    //                     if (checkers.white[i].x==p.x+150&&checkers.white[i].y==p.y-150||checkers.black[i].x==p.x+150&&checkers.black[i].y==p.y-150){
    //                         a=false;
    //                     }

    //                 }
    //                 if (a){ 
    //                     int[] arr={p.x-checkers.cell_side,p.y+checkers.cell_side,p.x+150,p.y-150};return arr;
    //                 }else {
    //                     int[]arr={p.x-checkers.cell_side,p.y+checkers.cell_side}; return arr;
    //                 }

    //             }else if (a1&&a2){int[] arr={p.x+checkers.cell_side,p.y+checkers.cell_side,p.x-checkers.cell_side,p.y-checkers.cell_side};return arr ;}
    //             else{ return null;}
    //         }else{ 




    //         }
    //     }


    //     //**************************************** */
    //     int[] pm=new int[26];
    //     if (!p.damma){
    //         boolean a1=true,a2=true,a3=true,a4=true;
    //         int k=0;
    //         int c=p.x,v=p.y; 

    //         for (int f=1;f<13;f++){
                
    //             for (int i=0;i<12;i++){
    //                 if (checkers.white[i].x==c+checkers.cell_side*f&&checkers.white[i].y==v+checkers.cell_side*f||checkers.black[i].x==c+checkers.cell_side*f&&checkers.black[i].y==v+checkers.cell_side*f){
    //                     a1=false;
    //                 }
    //                 if (checkers.white[i].x==c-checkers.cell_side*f&&checkers.white[i].y==v+checkers.cell_side*f||checkers.black[i].x==c-checkers.cell_side*f&&checkers.black[i].y==v+checkers.cell_side*f){
    //                     a2=false;
    //                 }
    //                 if (checkers.white[i].x==c+checkers.cell_side*f&&checkers.white[i].y==v-checkers.cell_side*f||checkers.black[i].x==c+checkers.cell_side*f&&checkers.black[i].y==v-checkers.cell_side*f){
    //                     a3=false;
    //                 }
    //                 if (checkers.white[i].x==c-checkers.cell_side*f&&checkers.white[i].y==v-checkers.cell_side*f||checkers.black[i].x==c-checkers.cell_side*f&&checkers.black[i].y==v-checkers.cell_side*f){
    //                     a4=false;
    //                 }
    //             }
    //             if(a1&&c+checkers.cell_side*f<checkers.width&&v+checkers.cell_side*f<checkers.height){
    //                 pm[k]=c+checkers.cell_side*f;
    //                 pm[k+1]=v+checkers.cell_side*f;
    //                 k+=2;
    //             }
    //             if(a2&&c+checkers.cell_side*f<checkers.width&&v+checkers.cell_side*f<checkers.height){
    //                 pm[k]=c-checkers.cell_side*f;
    //                 pm[k+1]=v+checkers.cell_side*f;
    //                 k+=2;
    //             }
    //             if(a3&&c+checkers.cell_side*f<checkers.width&&v+checkers.cell_side*f<checkers.height){
    //                 pm[k]=c+checkers.cell_side*f;
    //                 pm[k+1]=v-checkers.cell_side*f;
    //                 k+=2;
    //             }
    //             if(a4&&c+checkers.cell_side*f<checkers.width&&v+checkers.cell_side*f<checkers.height){
    //                 pm[k]=c-checkers.cell_side*f;
    //                 pm[k+1]=v-checkers.cell_side*f;
    //                 k+=2;
    //             }
    //                 // System.out.println(checkers.black[i].x+" "+(c+checkers.cell_side*f)+" "+checkers.black[i].y+" "+(v+checkers.cell_side*f)+"   "+f+"  "+i); 
    //         }
            //************************************** */



        //     while (k==0){   // kaaed ydour fel array w ki ycheki l piece loula, elli heyya tabda b3ida barsha, mayhemmoush fel bkeyya li f ro93a so yekhou les moves l possibles lkoll
        //         int f=0;
        //         // kaaed ydour fel array w ki ycheki l piece loula, elli heyya tabda b3ida barsha, mayhemmoush fel bkeyya li f ro93a so yekhou les moves l possibles lkoll
        //         // radditou yemchi diognal w jawwou behy 
        //         for (int i=0;i<12;i++){
        //             if (!(checkers.white[i].x==c+checkers.cell_side+f&& checkers.white[i].y==v+checkers.cell_side+f||checkers.black[i].x==c+checkers.cell_side+f&&checkers.black[i].y==v+checkers.cell_side+f)&&a1&&c+checkers.cell_side+f<checkers.width&&v+checkers.cell_side+f<checkers.height){
        //                 pm[k]=c+checkers.cell_side+f;
        //                 pm[k+1]=v+checkers.cell_side+f;
        //                 k+=2;   
        //                 System.out.println(k+" 1"); 
        //             }else{a1=false;}
        //             if (!(checkers.white[i].x==c-checkers.cell_side-f&& checkers.white[i].y==v+checkers.cell_side+f||checkers.black[i].x==c-checkers.cell_side-f&&checkers.black[i].y==v+checkers.cell_side+f)&&a2&&c-checkers.cell_side-f<checkers.width&&v+checkers.cell_side+f<checkers.height){
        //                 pm[k]=c-checkers.cell_side-f;
        //                 pm[k+1]=v+checkers.cell_side+f;
        //                 k+=2;    
        //                 System.out.println(k+" 2"); 
                        
        //             }else{a2=false;}
        //             if (!(checkers.white[i].x==c+checkers.cell_side+f&& checkers.white[i].y==v-checkers.cell_side-f||checkers.black[i].x==c+checkers.cell_side+f&&checkers.black[i].y==v-checkers.cell_side-f)&&a3&&c+checkers.cell_side+f<checkers.width&&v-checkers.cell_side-f<checkers.height){
        //                 pm[k]=c+checkers.cell_side+f;
        //                 pm[k+1]=v-checkers.cell_side-f;
        //                 k+=2;   
        //                 System.out.println(k+" 3"); 
                        
        //             }else{a3=false;}
        //             if (!(checkers.white[i].x==c-checkers.cell_side-f&& checkers.white[i].y==v-checkers.cell_side-f||checkers.black[i].x==c-checkers.cell_side-f&&checkers.black[i].y==v-checkers.cell_side-f)&&a4&&c-checkers.cell_side-f<checkers.width&&v-checkers.cell_side-f<checkers.height){
        //                 pm[k]=c-checkers.cell_side-f;
        //                 pm[k+1]=v-checkers.cell_side-f;
        //                 k+=2;    
        //                 System.out.println(k+" 4"); 

        //             }else{a4=false;}
        //             f+=checkers.cell_side;
        //         }
        //     }
        // }
        // if (p.damma){
        //     return pm; 
        // }
    //     System.out.println(Arrays.toString(pm));
    // }
    // return pm;

    // }
}  



