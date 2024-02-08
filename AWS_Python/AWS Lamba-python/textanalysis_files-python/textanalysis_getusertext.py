import json
import logging
import boto3
from botocore.exceptions import ClientError


logger = logging.getLogger()
logger.setLevel("INFO")


s3 = boto3.client("s3")
sns = boto3.client("sns")

topic_arn ="arn:aws:sns:eu-west-1:609980354649:textanalysis_gettext"


def lambda_handler(event, context):
    try:
        logger.info("inside handler function!!!!")
        query_parameters = event.get("queryStringParameters", {})
        sns_parameters = event.get('Records', [])
        if "text" in query_parameters:
            text=query_parameters["text"]
            logger.info(text)        
            sns.publish(TopicArn=topic_arn, Message=text)
            
            response = {
            'headers': {
            'Access-Control-Allow-Headers': 'Content-Type',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'OPTIONS,POST,GET'
        },
        'statusCode': 200,
        'body': json.dumps('almost done.click on result button')
         }
        
        else:
            print("Text parameter not found in the query parameters.")            
                   
    except ClientError:
        logger.error("An error occurred", exc_info=True)
        raise
    
    return response

