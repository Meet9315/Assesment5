package org.example;
import java.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int stu;
        Scanner sc= new Scanner(System.in);
        stu=sc.nextInt();
        String grade="";
        if(stu>=90 && stu<=100){
            grade="S";
        }
        else if(stu>=80 && stu<90){
            grade="A";
        }
        else if(stu>=70 && stu<80){
            grade="B";
        }
        else if(stu>=60 && stu<70){
            grade="C";
        }
        else{
            grade="E";
        }
        System.out.println("Grade = "+grade);
    }
}
SONARCUBE INSTALLATION
Step-1:
Install the PostgreSQL
sudo apt install -y postgresql-common postgresql -y
Step-2:
Enable the PostgreSQL database server to automatically start at boot.
sudo systemctl enable postgresql
Step-3:
Start the database server.
sudo systemctl start postgresql
Step-4:
Log in to the database
sudo -u postgres psql
Step-5:
Create a new sonaruser role with a strong password. Replace your_password with your new password.
create role sonaruser with login encrypted password
'your_password';
Step-6:
Create a new sonarqube database.
create database sonarqube;
Step-7:
Grant the sonaruser role full privileges to the sonarqube database.
grant all privileges on database sonarqube to sonaruser;
Step-8:
Switch to the sonarqube database.
\c sonarqube
grant all privileges on schema public to sonaruser;
Step-9:
Exit the database console.
\q
INSTALL SONARQUBE
Step-1:
Update the server APT package index.
sudo apt update
Step-2:
Install OpenJDK 17.
sudo apt install openjdk-17-jdk -y
Step-3:
Install Unzip to extract files from the SonarQube archive.
sudo apt install unzip
Step-4:
Verify the installed java version.
java -version
Step-5:
Extract files from the downloaded archive using Unzip.
unzip sonarqube-zip
Step-6:
Move the extracted files to a systemwide directory .
sudo mv sonarqube-/opt/sonarqube
Step-7:
sudo adduser --system --no-create-home --group --disabled-login sonarqube
Step-8:
Grant full privileges
sudo chown -R sonarqube:sonarqube /opt/sonarqube
INSTALL SONARSCANNER CLI
Step-1:
Visit and verify the SonarScanner website to check for latest version
Step-2:
Extract files from achieve
unzip sonar-scanner-cli-linux-x64.zip
Step-3:
Move the extracted directory to /opt/sonarscanner.
sudo mv sonar-scanner-linux-x64/ /opt/sonarscanner
Step-4:
Open the sonar-scanner.properties configuration file.
sudo nano/opt/sonarscanner/conf/sonar-scanner.properties
Step-5:
Find the following sonar.host.url directive and change the default url
sudo chmod +x /opt/sonarscanner/bin/sonar-scanner
Step-6:
sudo ln -s/opt/sonarscanner/bin/sonar-scanner /usr/local/bin/sonar-scanner
Step-7:
View the installed SonarScanner version.
sonar-scanner -v
CONFIGURE SONARQUBE
Step-1:
Open the main sonar.properties Sonarqube configuration file.
sudo nano /opt/sonarqube/conf/sonar.properties
Step-2:
Add the following configurations at the end of the file. Replace sonaruser and
your_password with actual database user details.
sonar.jdbc.username=sonaruser
sonar.jdbc.password=your_password
sonar.jdbc.url=jdbc:postgresql://localhost:5432/sonarqube
sonar.web.javaAdditionalOpts=-server
sonar.web.host=0.0.0.0
sonar.web.port=9000
sudo nano /etc/sysctl.conf
Step-3:
Add the following directives at the end of the file.
vm.max_map_count=524288
fs.file-max=131072
sudo nano /etc/security/limits.d/99-sonarqube.conf
Step-4:
Add the following directives to increase the file descriptor and process limits for
SonarQube.
sonarqube - nofile 131072
sonarqube - nproc 8192
sudo ufw allow 9000/tcp
sudo apt install ufw -y && sudo ufw allow 22/tcp
Step-5:
Reload UFW to apply the firewall configurations.
sudo ufw reload
Step-6:- View the UFW status
sudo ufw status
SETUP SONARQUBE AS A SYSTEM SERVICE
Step-1:
Create a new sonarqube.service file.
sudo nano /etc/systemd/system/sonarqube.service
Step-2:
Add the following configurations to the file.
Step-3:
Enable SonarQube to start at boot.
sudo systemctl enable sonarqube
Step-4:
Start the SonarQube service.
sudo systemctl start sonarqube
Step-5:
 View the SonarQube service status and verify that it's running.
sudo systemctl status sonarqube
sudo reboot now
