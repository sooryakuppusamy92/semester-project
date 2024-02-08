import json
import logging
import boto3
from botocore.exceptions import ClientError
from boto3.dynamodb.conditions import Key

logger = logging.getLogger()
logger.setLevel("INFO")

comprehend = boto3.client("comprehend")
sns = boto3.client("sns")

def lambda_handler(event, context):
    try:
        logger.info("inside handler function!!!!")   
        sns_parameters = event.get('Records', [])  

        if sns_parameters:
                # Extract the message from the SNS event
                for record in sns_parameters:
                    # Get the 'Sns' key from the first record
                    sns_data = record.get('Sns', {})
                    # Get the 'Message' key from the 'Sns' data
                    message = sns_data.get('Message', None)
                
                    if message is not None:
                        # Process the message
                        print("Message:", message)
                        response=look_imageMappingValue(message)
                        
                         # Publish the message
                        response = sns.publish(
                        TopicArn='arn:aws:sns:eu-west-1:609980354649:textanalysis_updateMapvalue',
                        Message=response,
                        Subject='getcomprehendresult'
                        )
                    else:
                        print("'Message' key not found in 'Sns' data.") 
        
    except ClientError:
        logger.error("An error occurred", exc_info=True)
        raise
    
def look_imageMappingValue(text_sentiment):
    try:
        table_name = "EmotionMapping"
            
        dynamodb = boto3.resource('dynamodb', region_name="eu-west-1")
        EmotionMappingtable = dynamodb.Table(table_name)
            
        response = EmotionMappingtable.query(
        KeyConditionExpression=Key('Emotion').eq(text_sentiment.upper()))
        print(response['Items'])
            
        for item in response['Items']:
                
            sentiment=item["Sentiment"]
            print(sentiment)
            
            emotionDisplayTable =dynamodb.Table("emotionDisplay")
                
            response1 =emotionDisplayTable.query(
            KeyConditionExpression=Key('Emotion').eq(sentiment.upper()))
            print(response1['Items'])
                
            for item in response1['Items']:
                print(item)
                bucket_name=item["S3ID"]
                file_name= item["Filename"]
                result=bucket_name + ":" + file_name
                logger.info(result) 
                return result
    except ClientError:
        logger.error("An error occurred", exc_info=True)
        raise
    
        
    