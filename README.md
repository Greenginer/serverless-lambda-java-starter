# Serverless java lambda starter/template

notable files:

* serverless-template.yml - baseline serverless template
* variables/shared-variables.yml - contains common variables to be set before deployment
* variables/dev-us-west-2-variables.yml - contains stage/region specific values

## Instructions for use

1. Open files under /variables directory add required values to files located there
2. Set [runtime](https://serverless.com/framework/docs/providers/aws/guide/services/#creation) in 'provider' section in serverless.yml
3. Add runtime [environment](https://serverless.com/framework/docs/providers/aws/guide/functions#environment-variables) variables. This is how serverless variables are used to configure lambda
4. Check LambdaRole, ensure it has the permissions required for your lambda use case
5. Check the deploymentBucket, this is an S3 location where the zip file is uploaded to, ensure the account deploying with has permissions to this bucket
6. Add any 'events' (see serverless [docs](https://serverless.com/framework/docs/providers/aws/guide/events/))

## Deployment requirements
* A bucket in S3 has been created for serverless to upload new deployments to
* **java:** projects: The code, including dependencies has been compiled into an uber-jar
* **java:** Set the 'artifact' to use the jar compiled in target/ [see](https://serverless.com/framework/docs/providers/aws/guide/packaging#artifact) docs on artifact

### Working example

From examples/java/app directory:

 `mvn verify`

`sls deploy -v -s test -v`

Run local first:

`sls invoke local -f FunctionOne`

`sls invoke -f FunctionOne`

Output from function will be:
`Hello <UUID>`

Clean up test deployment with:

`sls remove -s test`