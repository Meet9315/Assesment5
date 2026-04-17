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



DOCKER INSTALLATION AND COMMANDS
Step 1: Download Docker Desktop
• Go to the official Docker website
• Download Docker Desktop for Windows
Step 2:Installe the file
• open the downloaded file.
• Start installation.
Step 3: Enable Required Features During installation :
 • WSL 2 (Windows Subsystem for Linux)
 • Virtual Machine Platform
Step 4: Complete Installation
• Click Install and wait for the process to complete.
Step 5: Start Docker Desktop
• 1. Open Docker Desktop from the Start menu
• 2. Accept the terms and conditions
Step 6: Verify Installation
• Open CMD and run
• docker –version
• If the Docker version is displayed, Docker has been successfully installed
DOCKER COMMANDS
1. To see the list of docker images in our machine: docker images
2. To download an image from docker hub: docker pull <image_name>
3. To upload an image into dockerhub: docker push <username>/<image_name>
4. To search for an images on docker hub: docker search <keyword>
5. To delete an image on docker host: docker rmi <image_name>
6. To create an image from a container: docker commit <container_id> <new_image_name>
7. To create an image from a dockerfile: docker build -t <image_name> .
8. To see the list of running containers: docker ps
9. To see the list of stopped and running containers: docker ps -a
10. To start a container: docker start <container_id>
11. To stop a container: docker stop <container_id>
12. To restart a container: docker restart <container_id>
13. To remove a stopped container: docker rm <container_id>
14. To remove a running container: docker rm -f <container_id>
15. To stop all the running containers: docker stop $(docker ps -q)
16. Creating a docker container: docker run <image_name>
16. To See Logs Generated by the Container
Command:
docker logs <container_id>
Explanation:
Displays logs generated by a container.
17. To See the Ports of a Container
Command:
docker port <container_id>
Explanation:
Displays the port mappings used by a container.
18. To Find Detailed Information About a Container
Command:
docker inspect <container_id>
Explanation:
Provides detailed JSON information about a container including network settings, IP address,
and configuration.
19. Creating a Docker Container
Command:
docker run <image_name>
Example:
docker run ubuntu
Interactive example:
docker run -it ubuntu bash
Explanation:
Creates and starts a new container using the specified Docker image.
20. To Inspect a Network
Command:
docker network inspect <network_name>
Example:
docker network inspect bridge
Explanation:
Displays detailed information about a Docker network
