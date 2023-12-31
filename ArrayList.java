
 class ArrayList {

    // store object will hold about 200 
    private Object[] add = new Object[1000];
    public  Object[] newList; // new object arry that will be used to copy the new array into to
    private int all = 0; // this will hold elment in the array

    // Add an elemnt to a place in the object array
    public void insert(Object next, int index) {

        // the index cant be larger than the element amount we have in the array
        if (index > all && index >0) {
            System.out.println("Please enter a number that is in bound of the: " + " index: " + index);
            return;
        }

        while  (all == add.length) {
            newList = new Object[add.length + 1]; // length of the new array +1 

            // increment and add to the new array list 
            for (int i = 0; i < index; i++) {
                newList[i] = add[i];
            }
            newList[index] = next;
            all++; // since we are adding a element we have to increment to the elemnt 
            for (int i = index; i < add.length; i++) {
                newList[i + 1] = add[i];
            }
            // update and add to the total

            add = newList;
        } 
        // if add is not equal to the length be able to impeplent the shift 
        if (all != add.length) {
            for (int k = all; k > index; k= k-1) {
                add[k] = add[k -1];
            }
            add[index] = next;
            all++;
        }
    }

    // Remove and return the object at the specified index
    public Object remove(int index) {
        if (index >0 && index !=0){
            all = all -1;  // remove a element we added to our all count 
            Object removeit = add[index];

            for (int i = index; i < all; i++) {
                add[i] = add[i + 1];
            }
            return removeit;

        }
        return 0; // the index is not part of the if statment and is invalid we return 0
    }

    // return the total element count
    public int all() {
        return all;
    }

    // We wabt to be able to print out and return our values with this string method 
    public String toString() {
        String read = "The elements are:  ";
        // use for loop to get each value in array
        for (int i = 0; i < all; i++) {
            read += add[i];
        }
        return read;
    }

    // check if array is empty
    public boolean isEmpty() {
        boolean empty = true;
        int find = all(); // this mean that the their is no elements in the array
        if (find == 0) {
            empty = true;
        }
        return empty;
    }

    // return the index of the specified target
    public int indexOf(Object Find) {
        int index;int k;
        if (Find ==null){
            return -1;
        }
        // use for loop to get the index
        for (k= 0; k < add.length; k++) {

            if (add[k] == Find) {
                index = k;
            }
        }
        return -1;
    }

    // Compare sizes and elements in the data structure
    public boolean equals(Object look) {
        // see if we are comaparing the same object
        if (look== this) {
            return true;
        }
        ArrayList o = (ArrayList) look;
        boolean size = (this.all() == o.all()); // compare with boolean to see if the sizes of the object array are equal
        if (size==true) {
            // loop through to find if each element is equal if one is not break out 
            for (int i = 0; i < this.all(); i++) {
                if (add[i] != o.add[i]) {
                    return false;
                }
            }
            return true;
        } else {

            return false;
        }
    }

    // Returns the object at index asked for 
    public Object get(int index) {
        return add[index];
    }
}

