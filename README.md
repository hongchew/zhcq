Please use alternateDocRoot as the alternatedocroot_1 when you deploy the project
glassfish.xml:
    <property name="alternatedocroot_1" value="from=/images/* dir=YOUR_PATH_HERE\zhcq-co\alternateDocRoot"/>
web.xml:
    <context-param>
        <param-name>alternatedocroot_1</param-name>
        <param-value>YOUR_PATH_HERE\zhcq-co\alternateDocRoot\images</param-value>
    </context-param>

# zhcq-co
IS3106 Group Project
