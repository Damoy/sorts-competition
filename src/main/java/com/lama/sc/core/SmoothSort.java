package com.lama.sc.core;

import javax.naming.spi.DirStateFactory.Result;

import com.lama.sc.model.IData;


// From https://github.com/ChrisKitching/JavaExternalSort/blob/master/src/uk/ac/cam/cdk23/fjava/tick0/SmoothSort.java


public final class SmoothSort implements ISort {
	
	private final static ISort INSTANCE = new SmoothSort();

	private SmoothSort(){}

	public static ISort getInstance(){
		return INSTANCE;
	}
	
    @Override
	public IData process(IData data) {
    		 return smoothSort(data, 0, data.getLength() - 1);	 
    }
	
    private static final int[] leonardoNumbers = {1,1,3,5,9,15,25,41,67,109,177,287,465,753,1219,1973,3193,5167,8361,13529,21891,35421, 57313,92735,150049,242785,392835,635621,1028457,1664079,2692537,4356617,7049155,11405773,18454929,29860703,48315633,78176337,126491971,204668309,331160281,535828591,866988873};

    public IData smoothSort(IData data, final int start, int end) {
        //SmoothSort the range specified (inclusive).
        //Since this function will be called with larger ranges the more sorted the array was (Since quicksort isn't
        //terribly good at dealing with sorted arrays), the adaptive properties of smoothSort should be rather handy.
    		
    		int[] input = data.get();
        int head = start;

        int p = 1; // the bitmap of the current standard concatenation >> pshift
        int pshift = 1;

        while (head < end-3) {
            //System.out.println("Processing head:"+head);
            if ((p & 3) == 3) {
                // Add 1 by merging the first two blocks into a larger one.
                // The next Leonardo number is one bigger.
                //System.out.println("p&3 == 3");
                sift(input, pshift, head);
                p >>>= 2;
                pshift += 2;
            } else {
                // adding a new block of length 1
                if ((leonardoNumbers[pshift - 1])*4 >= end-3 - head) {//TODO?
                    // this block is its final size.
                    trinkle(input, p, pshift, head, false);
                } else {
                    // this block will get merged. Just make it trusty.
                    sift(input, pshift, head);
                }

                if (pshift == 1) {
                    // LP[1] is being used, so we add use LP[0]
                    p <<= 1;
                    pshift--;
                } else {
                    // shift out to position 1, add LP[1]
                    p <<= (pshift - 1);
                    pshift = 1;
                }
            }
            p |= 1;
            head+=4;
        }
        //head -= 4;

        trinkle(input, p, pshift, head, false);


        while (pshift != 1 || p != 1) {
            if (pshift <= 1) {
                // block of length 1. No fiddling needed
                //int trail;// = Integer.numberOfTrailingZeros(p & ~1);
                final int pa1 = p & ~1;
                int trail = 32;
                int v = pa1 & -pa1;
                if (v != 0) trail--;
                if ((v & 0x0000FFFF) != 0) trail -= 16;
                if ((v & 0x00FF00FF) != 0) trail -= 8;
                if ((v & 0x0F0F0F0F) != 0) trail -= 4;
                if ((v & 0x33333333) != 0) trail -= 2;
                if ((v & 0x55555555) != 0) trail -= 1;
                p >>>= trail;
                pshift += trail;
            } else {
                p <<= 2;
                p ^= 7;
                pshift -= 2;

                // This block gets broken into three bits. The rightmost
                // bit is a block of length 1. The left hand part is split into
                // two, a block of length LP[pshift+1] and one of LP[pshift].
                // Both these two are appropriately heapified, but the root
                // nodes are not necessarily in order. We therefore semitrinkle
                // both of them
                //   System.out.println("head:"+head);
                //   System.out.println("leo:"+(leonardoNumbers[pshift])*4 );
                //   System.out.println("Passing:"+( head - (leonardoNumbers[pshift])*4 - 4));

                trinkle(input, p >>> 1, pshift + 1, (head - 4) - ((leonardoNumbers[pshift])*4)  , true);
                trinkle(input, p, pshift, head - 4, true);
            }

            head-=4;
        }
        
        return data;
        //sift(2, end-11);

    }

    private static final void sift(int[] input, int pshift,int head) {
        //Considering head as the root node of a heap of size L(pshift), restoe it's heap property.

        final int int1 = input[head];
        final int int2 = input[head+1];
        final int int3 = input[head+2];
        final int int4 = input[head+3];
        int val = (((int1 & 0xff) << 24) | ((int2 & 0xff) << 16) | ((int3 & 0xff) << 8) | (int4 & 0xff)); //The value
        //starting at head.

        while (pshift > 1) {
            int rt = head - 4;
            int lf = head - 4 - (leonardoNumbers[pshift - 2])*4;

            //Left child
            final int lint1 = input[lf];
            final int lint2 = input[lf+1];
            final int lint3 = input[lf+2];
            final int lint4 = input[lf+3];
            final int lfval = (((lint1 & 0xff) << 24) | ((lint2 & 0xff) << 16) | ((lint3 & 0xff) << 8) | (lint4 & 0xff)); //The value

            //Right child
            final int rint1 = input[rt];
            final int rint2 = input[rt+1];
            final int rint3 = input[rt+2];
            final int rint4 = input[rt+3];
            final int rtval = (((rint1 & 0xff) << 24) | ((rint2 & 0xff) << 16) | ((rint3 & 0xff) << 8) | (rint4 & 0xff)); //The value

            if (val >= lfval && val >= rtval) {
                //Heap satisfactory.
                break;
            }
            //Swap head with largest child...
            if (lfval >= rtval) {
                input[head] = lint1;
                input[head+1] = lint2;
                input[head+2] = lint3;
                input[head+3] = lint4;
                head = lf;
                pshift -= 1;
            } else {
                input[head] = rint1;
                input[head+1] = rint2;
                input[head+2] = rint3;
                input[head+3] = rint4;
                head = rt;
                pshift -= 2;
            }
        }

        input[head] = int1;
        input[head+1] = int2;
        input[head+2] = int3;
        input[head+3] = int4;
    }

    private static final void trinkle(int[] input, int p,int pshift, int head, boolean isTrusty) {
        //Heap with root at head has the heap property - now restoring the string property.


        final int int1 = input[head];
        final int int2 = input[head+1];
        final int int3 = input[head+2];
        final int int4 = input[head+3];
        int val = (((int1 & 0xff) << 24) | ((int2 & 0xff) << 16) | ((int3 & 0xff) << 8) | (int4 & 0xff)); //The value

        while (p != 1) {
            int stepson = head - (leonardoNumbers[pshift])*4;

            final int sint1 = input[stepson];
            final int sint2 = input[stepson+1];
            final int sint3 = input[stepson+2];
            final int sint4 = input[stepson+3];
            int stepval = (((sint1 & 0xff) << 24) | ((sint2 & 0xff) << 16) | ((sint3 & 0xff) << 8) | (sint4 & 0xff)); //The value

            if (stepval <= val) {
                break; // current node is greater than head. Sift.
            }

            // no need to check this if we know the current node is trusty,
            // because we just checked the head (which is val, in the first
            // iteration)
            if (!isTrusty && pshift > 1) {
                int rt = head - 4;
                int lf = head - 4 - (leonardoNumbers[pshift - 2])*4;

                //Left child
                final int lint1 = input[lf];
                final int lint2 = input[lf+1];
                final int lint3 = input[lf+2];
                final int lint4 = input[lf+3];
                final int lfval = (((lint1 & 0xff) << 24) | ((lint2 & 0xff) << 16) | ((lint3 & 0xff) << 8) | (lint4 & 0xff)); //The value

                //Right child
                final int rint1 = input[rt];
                final int rint2 = input[rt+1];
                final int rint3 = input[rt+2];
                final int rint4 = input[rt+3];
                final int rtval = (((rint1 & 0xff) << 24) | ((rint2 & 0xff) << 16) | ((rint3 & 0xff) << 8) | (rint4 & 0xff)); //The value

                if (rtval >= stepval || lfval  >= stepval) {
                    break;
                }
            }

            input[head] = sint1;
            input[head+1] = sint2;
            input[head+2] = sint3;
            input[head+3] = sint4;

            head = stepson;
            //int trail; = Integer.numberOfTrailingZeros(p & ~1);

            final int pa1 = p & ~1;
            int trail = 32;
            int v = pa1 & -pa1;
            if (v != 0) trail--;
            if ((v & 0x0000FFFF) != 0) trail -= 16;
            if ((v & 0x00FF00FF) != 0) trail -= 8;
            if ((v & 0x0F0F0F0F) != 0) trail -= 4;
            if ((v & 0x33333333) != 0) trail -= 2;
            if ((v & 0x55555555) != 0) trail -= 1;
            p >>>= trail;
            pshift += trail;
            isTrusty = false;
        }

        if (!isTrusty) {
            input[head] = int1;
            input[head+1] = int2;
            input[head+2] = int3;
            input[head+3] = int4;
            sift(input, pshift, head);
        }
    }

    public static final void insertSort(int[] input, int start, int en) {  //Binary insort from start to en inclusive.
        //Sort range from start to en inclusive.
        int pointer = start+4;
        while (pointer < en+1) {
            final int int1 = input[pointer];
            final int int2 = input[pointer+1];
            final int int3 = input[pointer+2];
            final int int4 = input[pointer+3];
            final int p = (((int1 & 0xff) << 24) | ((int2 & 0xff) << 16) | ((int3 & 0xff) << 8) | (int4 & 0xff));
            final int end = pointer - 4;  //End of sorted range.
            int l = start;
            int r = pointer - 4;
            while (l != r) {
                //Binary search in the already sorted range to find the proper position of the next unsorted element, p.
                int middle = (l + r) >>> 1;
                while (middle % 4 != 0) {
                    middle++;
                }
                if (middle == r) {
                    middle -= 4;
                }
                final int middleInt = (((input[middle] & 0xff) << 24) | ((input[middle+1] & 0xff) << 16) | ((input[middle+2] & 0xff) << 8) | (input[middle+3] & 0xff));
                if (p > middleInt) {
                    l = middle + 4;
                } else {
                    r = middle;
                }
            }
            if (r == end && p > (((input[end] & 0xff) << 24) | ((input[end+1] & 0xff) << 16) | ((input[end+2] & 0xff) << 8) | (input[end+3] & 0xff))) {
                l+=4;
            }
            //Now to put p into it's proper place...
            System.arraycopy(input, l, input, l + 4, end - l + 4);
            input[l] = int1;
            input[l+1] = int2;
            input[l+2] = int3;
            input[l+3] = int4;
            pointer+=4;
        }
    }


}
