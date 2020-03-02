//library 'jira10'_
pipeline{
    agent any
    stages{
        stage('jenkins'){
            steps{
                script{
                     jiraCollector.inprogress(json)
                     jiraCollector.done(json)
                     jiraCollector.todo(json)
                      }
            }
        }
    }
    
}
