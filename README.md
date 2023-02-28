# AutoScroll
Autoscroll recyclerview items

> Step 1. Add the JitPack repository to your build file

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
   ```
   
> Step 2. Add the dependency

```gradle

dependencies {
	        api 'com.github.Tusharshetty912:AutoScroll:1.0.5'
	}
  ```
  
  > Step 3. Call the function 
  
  ``` 
  
   AutoScroll.setAutoScrollInfo(adapter.itemCount,recyclerview,delayInMilliSecs)
   
   #delayInMilliSecs is the time required for waiting in a particular card
   
   ```
