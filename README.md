Used Terraform to automate the launch of ec2instance and installation of jenkins, docker,SonarQube, Trivy file scan, inside of the ec2 instance 
Trivy file scan this will be used to help launch the docker container 

Git - For source control and Git checkout
SonarQube - This would work with Jenkins for our code analysis and test whether our code has any errors or not 
Trivy File scan - This would be used to scan every file, dependencies and artifact and will then use it to build a image/container
Docker - Docker images would be pushed to dockerhub and with this trivy image scan would be used to scan the image from docker hub 
Trivy image scan - This will be used to scan the docker image and with the help of Trivy image scan we'll be able to run a container from our particular image.

SonarQube Trivy file scan and OWASP are used for security checks with the dev sec practice
![Screenshot 2024-04-17 125606](https://github.com/Sehindemi/Amazon-FrontEnd-DevOps-Project/assets/97199481/21627d71-2615-4ce7-b406-923f66264f0e)


Jenkins Action Pipeline
![Screenshot 2024-04-17 182007](https://github.com/Sehindemi/Amazon-FrontEnd-DevOps-Project/assets/97199481/f09d7af4-5f16-4660-b210-50bcec978469)

![Screenshot 2024-04-17 182007](https://github.com/Sehindemi/Amazon-FrontEnd-DevOps-Project/assets/97199481/19c87f7c-eedd-4261-9511-832c35fadd5a)
