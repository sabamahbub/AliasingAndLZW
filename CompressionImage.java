public class CompressionImage{

    Image3 firstImage;
    Image3 noFilter;
    Image3 filter1;
    Image3 filter2;
    // int equalNumerators = {1, 1, 1, 1, 1, 1, 1, 1, 1};
    // int weightedNumerators = {1, 2, 1, 2, 4, 2, 1, 2, 1};
    int k, m, n;
    public CompressionImage(int x, int y, int k, int m, int n){
        this.k = k;
        this.m = m;
        this.n = n;
        this.firstImage = new Image3(x, y);
        this.noFilter = new Image3(x/k, y/k);
        this.filter1 = new Image3(x/k, y/k);
        this.filter2 = new Image3(x/k, y/k);
        firstImage.drawEmpty();
        firstImage.drawCircle(m, n);
    }

    public void withOutFilter(){
        int[] irgb = new int[3];
        for(int y = 0; y < noFilter.getH(); y++){
            for(int x = 0; x < noFilter.getW(); x++){
                firstImage.getPixel(x*k, y*k, irgb);
                noFilter.setPixel(x, y, irgb);
            }
        }
    }

     public void filterOne(){
        int[] irgb = new int[3];
        // for(int y = 0; y < noFilter.getH(); y++){
        //     for(int x = 0; x < noFilter.getW(); x++){
        for(int x = 0; x < filter1.getW(); x++){
            for(int y = 0; y < filter1.getH(); y++){
                filterOnePixel(x, y, irgb);
                filter1.setPixel(x, y, irgb);
            }
        }
    }

    public void filterTwo(){
        int[] irgb = new int[3];
        // for(int y = 0; y < noFilter.getH(); y++){
        //     for(int x = 0; x < noFilter.getW(); x++){
        for(int x = 0; x < filter2.getW(); x++){
            for(int y = 0; y < filter2.getH(); y++){
                filterTwoPixel(x, y, irgb);
                filter2.setPixel(x, y, irgb);
            }
        }
    }

    public void filterOnePixel(int xPrime, int yPrime, int[] irgb){
        int x = xPrime * k;
        int y = yPrime * k;
        int denomonator = 0;
        int[] specificPixel = new int[3];
        int[] value = {0,0,0};

        //topRight
        if((x-1)>=0 && (y-1)>=0){
            firstImage.getPixel(x-1, y-1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        }
        //top
        if((y-1)>=0){
            firstImage.getPixel(x, y-1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        }
        //topleft
        if((x+1)<firstImage.getW() && (y-1)>=0){
            firstImage.getPixel(x+1, y-1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        }
        //left
        if((x-1)>=0){
            firstImage.getPixel(x-1, y, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        }
        //center
            firstImage.getPixel(x, y, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        //right
        if((x+1)<firstImage.getW()){
            firstImage.getPixel(x+1, y, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        }
        //bottomLeft
        if((x-1)>=0 && (y+1)<firstImage.getH()){
            firstImage.getPixel(x-1, y+1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        }
        //bottom
        if((y+1)<firstImage.getH()){
            firstImage.getPixel(x, y+1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        }
        //bottomRight
        if((x+1)<firstImage.getW() && (y+1)<firstImage.getH()){
            firstImage.getPixel(x+1, y+1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += specificPixel[i];
            denomonator++;
        }
        //average
        for(int i = 0;i<3;i++) {
            value[i] = value[i]/denomonator;
            if (value[i] > 255) value[i] = 255;
            else if (value[i] < 0) value[i] = 0;
        }
        for(int i = 0;i<3;i++) irgb[i] = value[i];
    }

    public void filterTwoPixel(int xPrime, int yPrime, int[] irgb){
        int x = xPrime * k;
        int y = yPrime * k;
        int denomonator = 0;
        int[] specificPixel = new int[3];
        int[] value = {0,0,0};

        //topLeft
        if((x-1)>=0 && (y-1)>=0){
            firstImage.getPixel(x-1, y-1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 1*specificPixel[i];
            denomonator += 1*1;
        }
        //top
        if((y-1)>=0){
            firstImage.getPixel(x, y-1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 2*specificPixel[i];
            denomonator += 1*2;
        }
        //topRight
        if((x+1)<firstImage.getW() && (y-1)>=0){
            firstImage.getPixel(x+1, y-1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 1*specificPixel[i];
            denomonator += 1*1;
        }
        //left
        if((x-1)>=0){
            firstImage.getPixel(x-1, y, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 2*specificPixel[i];
            denomonator += 1*2;
        }
        //center
            firstImage.getPixel(x, y, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 4*specificPixel[i];
            denomonator += 1*4;
        //right
        if((x+1)<firstImage.getW()){
            firstImage.getPixel(x+1, y, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 2*specificPixel[i];
            denomonator += 1*2;
        }
        //bottomLeft
        if((x-1)>=0 && (y+1)<firstImage.getH()){
            firstImage.getPixel(x-1, y+1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 1*specificPixel[i];
            denomonator += 1*1;
        }
        //bottom
        if((y+1)<firstImage.getH()){
            firstImage.getPixel(x, y+1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 2*specificPixel[i];
            denomonator += 1*2;
        }
        //bottomRight
        if((x+1)<firstImage.getW() && (y+1)<firstImage.getH()){
            firstImage.getPixel(x+1, y+1, specificPixel);
            for(int i = 0;i<3;i++) value[i] += 1*specificPixel[i];
            denomonator += 1*1;
        }
        //average
        for(int i = 0;i<3;i++) {
            value[i] = value[i]/denomonator;
            if (value[i] > 255) value[i] = 255;
            else if (value[i] < 0) value[i] = 0;
        }
        for(int i = 0;i<3;i++) irgb[i] = value[i];
    }

    public void driver(){
        withOutFilter();
        filterOne();
        filterTwo();
        firstImage.write2PPM("Circle_M"+m+"_N"+n+"_K"+k+"_Original.ppm");
        noFilter.write2PPM("Circle_M"+m+"_N"+n+"_K"+k+"_NoFilter.ppm");
        filter1.write2PPM("Circle_M"+m+"_N"+n+"_K"+k+"_Filter1.ppm");
        filter2.write2PPM("Circle_M"+m+"_N"+n+"_K"+k+"_Filter2.ppm");
        firstImage.display("Circle_M"+m+"_N"+n+"_K"+k+"_Original.ppm");
        noFilter.display("Circle_M"+m+"_N"+n+"_K"+k+"_NoFilter.ppm");
        filter1.display("Circle_M"+m+"_N"+n+"_K"+k+"_Filter1.ppm");
        filter2.display("Circle_M"+m+"_N"+n+"_K"+k+"_Filter2.ppm");
    }
}