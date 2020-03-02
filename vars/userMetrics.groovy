/*
collecting all issues with status Done in a project
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}%20AND%20(status%3DDONE)
collecting all issues with status To-do in a project
  sh "curl -X GET -i -H  -d  -u username:passwd http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D'${projectName}'%20AND%20(status%3D"To%20Do")"
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}%20AND%20(status%3D'\'"To%20Do"\'')
collecting all issues with status In-Progress in a project
  sh "curl -X GET -i -H  -d  -u username:passwd http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D'${projectName}'%20AND%20(status%3D"In%20Progress") "
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}%20AND%20(status%3D'\'"In%20Progress"\'')
collecting all issues assigned to a user with username which are in done state
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=assignee='${emailid/username}'%20AND%20(status%3DDONE) 
collecting all issues assigned to a user with username with status "To-do"
  sh "curl -X GET -i -H  -d  -u username:passwd http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=assignee='${emailid/username}'%20AND%20(status%3D"To%20Do") "
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=assignee='${emailid/username}'%20AND%20(status%3D'\'"To%20Do"\'')
collecting all issues assigned to a user with username with status "in-progress"
  sh "curl -X GET -i -H  -d  -u username:passwd http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=assignee='${emailid/username}'%20AND%20(status%3D"In%20Progress") "
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=assignee='${emailid/username}'%20AND%20(status%3D'\'"In%20Progress"\'')
*/

import groovy.json.* 

def done(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName

  withCredentials([usernamePassword(credentialsId: 'jira_password', passwordVariable: 'password', usernameVariable:'username')]){
sh """
     curl -X GET \
    -H -d -u $username:$password \
     'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=assignee='${emailid/username}'%20AND%20(status%3DDONE)' \
  -H 'cache-control: no-cache' 
  """
   
  }
}




  
def inprogress(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName

  withCredentials([usernamePassword(credentialsId: 'jira_password', passwordVariable: 'password', usernameVariable:'username')]){
sh """
     curl -X GET \
    -H -d -u $username:$password \
     'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=assignee='${emailid/username}'%20AND%20(status%3D'\'"In%20Progress"\'')' \
  -H 'cache-control: no-cache' 
  """
   
  }
}





def todo(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName

  withCredentials([usernamePassword(credentialsId: 'jira_password', passwordVariable: 'password', usernameVariable:'username')]){
sh """
     curl -X GET \
    -H -d -u $username:$password \
     'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}%20AND%20(status%3D'\'"To%20Do"\'')' \
  -H 'cache-control: no-cache' 
  """
   
  }
}




}
