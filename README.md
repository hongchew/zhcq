# zhcq-co
A fashion ecommerce platform created for National University of Singapore's IS3106 Enterprise Systems Interface Design and Development.

## IS3106 Group Project (Group 11)
- Koh Zhi Ming (A0171235M)
- Lim Cheng Yang (A0173678M)
- Lim Qian Yi (A0172622L)
- Toh Hong Chew (A0167436X)



## Instructions:
- Please run the Ionic application in mobile mode on the browser for a better experience
- Please use alternateDocRoot found in this folder as the alternatedocroot_1 when you deploy the project. Please add the following lines to:
  - glassfish.xml:
    ```
    <property name="alternatedocroot_1" value="from=/images/* dir=YOUR_PATH_HERE\zhcq-co\alternateDocRoot"/>
    ```
  - web.xml:
    ```
    <context-param>
        <param-name>alternatedocroot_1</param-name>
        <param-value>YOUR_PATH_HERE\zhcq-co\alternateDocRoot\images</param-value>
    </context-param>
    ```     
    

