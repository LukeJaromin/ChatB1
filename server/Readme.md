
Chat is the server-client application using Jms and the REST architecture.
The client application works in a console.
Server is deployed on WildFly 25.0.1.Final.
Database is configured in ```docker-compose.yml``` file.

# Description of functionality:

## Client registration
Enter the username in the console. It should consist only of letters and numbers and should be a single word.

## "Security"
From this point on, the username is passed implicitly through the header, which can be thought of as a
security simulation.

## Private messages
command: ```PRIVATE_MESSAGE```.
Sends a message to the specified user. If provided username is not in the database, error is returned to the sender.

## Public messages
command: ```PUBLIC_MESSAGE```.
Sends a message to all users.

## Channel creation
command: ```CREATE_CHANNEL```.
Creates channel with a given name. Error message is returned if channel already exists.

## Adding user to channel
command:```ADD_USER_TO_CHANNEL```.
Only members of a given channel can add other members.
After running this command, user will be asked to enter the name of the channel and the name of the user to be added.

## Exit a channel
command: ```LEAVE_CHANNEL```.
Only works for members of a given channel.
You cannot remove another user from the channel.

## Send messages to a channel
command: ```CHANNEL_MESSAGE```.

# Transferring files
command ```SEND_FILE```.
The file will be uploaded and download link will be sent to receiver. 

# Record and read channel history
command: ```HISTORY_CHANNEL```.
Displays the history of conversations from a given channel.

All available server requests are stored in the ```Chat_postman_collection.json``` file attached to the project.
