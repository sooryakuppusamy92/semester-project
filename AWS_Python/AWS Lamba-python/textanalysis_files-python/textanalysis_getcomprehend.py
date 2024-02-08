import json
import logging
import boto3
from botocore.exceptions import ClientError

logger = logging.getLogger()
logger.setLevel("INFO")

comprehend = boto3.client("comprehend")
sns = boto3.client("sns")

def lambda_handler(event, context):
    try:
        logger.info("inside handler function!!!!")  
        logger.info(event)  
        sns_parameters = event.get('Records', [])  
        logger.info(sns_parameters)   
        if sns_parameters:
                # Extract the message from the SNS event
                for record in sns_parameters:
                    # Get the 'Sns' key from the first record
                    sns_data = record.get('Sns', {})
                    logger.info(sns_data) 
                    # Get the 'Message' key from the 'Sns' data
                    message = sns_data.get('Message', None)
                
                    if message is not None:
                        # Process the message
                        print("sns Message:", message)
                       
                    else:
                        print("'Message' key not found in 'Sns' data.")   
        #text=event['Records'][0]['Sns']['Message']
        logger.info(message)
        #code to detect sentiment of text entered by user
        sentiment = comprehend.detect_sentiment(Text = message, LanguageCode = "en")
        logger.info(sentiment)
        
        #text sentiment output
        text_sentiment=sentiment["Sentiment"]
        logger.info(text_sentiment)
        
        # Publish the message
        response = sns.publish(
        TopicArn='arn:aws:sns:eu-west-1:609980354649:textanalysis_getcomprehendresult',
        Message=text_sentiment,
        Subject='getcomprehendresult'
    )
        
    except ClientError:
        logger.error("An error occurred", exc_info=True)
        raise
    
    
    