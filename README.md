Version Maven Plug-in
====================

This is **not** the [Codehaus Versions plug-in](http://mojo.codehaus.org/versions-maven-plugin/). The two plug-ins serve entirely different purposes.

This Maven plug-in injects the parts of the standard project version into 
individual variables for easy re-use.

A standard project version is assume to follow the format *(PREFIX)?MAJOR(.MINOR)?(.BUILD)?(.REVISION)?(SUFFIX)?* where only the MAJOR component is required. 

For example, if the project version is *1.0.0-SNAPSHOT* but some file name should not contain the *-SNAPSHOT* version suffix, this plug-in parses the version string and creates a variable named *version.string* with just the *1.0.0* portion of the project’s version string.

# Configuration
Configuring the plug-in is easy, simply add the following information to your POM’s plug-ins section:

```xml
<plugin>
	<groupId>com.lostcreations</groupId>
	<artifactId>maven-version-plugin</artifactId>
	<version>1.0.0</version>
</plugin>
```

The plug-in executes a single goal, *version*, in the *initialize* phase. This means the version variables are available very early in the build life-cycle.

# Version Variables
The default variable names are:

* **version.string** The version sans prefix and/or suffix. 
	* For *1.1.0-SNAPSHOT* the value *1.1.0* would be stored in the *version.string* variable.
* **version.string.4** The version sans prefix and/or suffix in a four-component version format. 
	* For *1.1.0-SNAPSHOT* the value *1.1.0.0* would be stored in the *version.string.4* variable.
* **version.string.raw.4** The version in a four-component version format with the prefix and suffix. 
	* For *1.1.0-SNAPSHOT* the value *1.1.0.0-SNAPSHOT* would be stored in the *version.string.raw.4* variable.
* **version.major** The MAJOR component of a version. 
	* For *1.1.0-SNAPSHOT* the value *1* would be stored in the *version.major* variable.
* **version.minor** The MINOR component of a version. 
	* For *1.1.0-SNAPSHOT* the value *1* would be stored in the *version.minor* variable.
* **version.build** The BUILD component of a version. 
	* For *1.1.0-SNAPSHOT* the value *0* would be stored in the *version.build* variable.
* **version.revision** The REVISION component of a version. 
	* For *1.1.0-SNAPSHOT*, since no REVISION component is specified in the version string, the value *0* would be stored in the *version.revision* variable. 

Any of the above version variable names are configurable via the standard plug-in configuration section. Simply append *.varname* to the default variable name in the configuration section, or via the command line, and the variable name can be configured.

For example, to put the parsed version string into a variable named *version.string.parsed* configure the plug-in as such:

```xml
<plugin>
	<groupId>com.lostcreations</groupId>
	<artifactId>maven-version-plugin</artifactId>
	<version>1.0.0</version>
	<configuration>
		<version.string.varname>version.string.parsed</version.string.varname>
	</configuration>
</plugin>
```