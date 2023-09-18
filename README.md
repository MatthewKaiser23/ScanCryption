# ScanCryption

[this is the link to my github repository](https://github.com/MatthewKaiser23/ScanCryptio)

## This is an image-to-text Android app created using Android Studio

## Description

The goal was to build an Android app that could take an image containing text, recognize the text within the image using an OCR, and then be able to decipher the text using a Monoalphabetic Substitution Cipher.

## Features

- Image-to-Text Conversion: Converting images containing text into readable text.
- Text Decryption: The Decryption works only for a specific encrypted text which will be discussed later in this project. 
- User-Friendly Interface: A basic User-Friendly interface is used to allow users to capture images or select them from their device's gallery.
- OCR Precision: An API was used to deliver accurate and reliable text recognition.
- Image Preprocessing: Before text recognition occurs, simple image preprocessing techniques are used, using the OpenCV library.
- Future Expansion: Possible future updates could be able to increase the effectiveness of recognizing text from an image under varying conditions.

## Technologies Used

- [Android Studio](https://developer.android.com/studio)
- [Android-Image-Cropper](https://github.com/ArthurHub/Android-Image-Cropper/tree/master)
- [OpenCV](https://opencv.org/)
- [api-ninjas](https://api-ninjas.com/api)
- Monoalphabetic Substitution Cipher cryptographic algorithm

## Challenges

There were many challenges in developing this Android app. The most notable being:

- Text within an image not being fully recognizable in certain conditions.

## How to Install and Run the Project

Follow these steps to set up the OCR Android app on your local development environment.

### Prerequisites

Before getting started, ensure you have the following prerequisites:

Make sure you have Android Studio installed. If not, download and install it from [here](https://developer.android.com/studio)

### Installation Steps


1. Clone the repository

2. Open the Project in Android Studio:
   
   - Launch Android Studio.
   - Choose "Open an existing Android Studio Project”.
   - Navigate to the directory where you cloned the repository and select the project folder.

3. Install Dependencies:
   
   - Android Studio will automatically prompt you to install any missing dependencies. Follow the on-screen instructions to do so.

4. Build and Run:
   
   - Connect your Android device to your computer via USB or use an Android Virtual Device (AVD).
   - Click the "Run" button (green triangle) in Android Studio to build and install the app on your device.

5. App Permissions:
   
   - Grant necessary permissions when prompted on your Android device, such as camera and storage access, to enable image capture and selection.

6. Start Using the App:
   
   - The OCR Android app will launch on your device. Use it to capture images with text and perform OCR and decryption.

## Troubleshooting

If you encounter any issues during installation or while running the app, consider the following tips:

- Ensure that your Android Studio and SDK are up to date.
- Double-check the permissions on your device to ensure the app has the necessary access.
- Verify that the device or AVD you are using meets the minimum Android version requirements.

## How to Use the Project

These Steps are:

1. Capture or select an image.
2. decrypt image.

## User Interface Overview:

[User Interface](https://github.com/MatthewKaiser23/ScanCryption/assets/91264497/6f3ea836-76f1-43da-9071-d4293e9d57ef)

## Basic Usage

- [Encrypted Text](https://github.com/MatthewKaiser23/ScanCryption/assets/91264497/8eac6c46-0ee9-4184-bb69-9d66ae59d947)

- ![Demo Video 1](https://github.com/MatthewKaiser23/ScanCryption/assets/91264497/c5bc9045-3385-4d50-a794-198ce8e8c3df)

- ![Demo Video 2](https://github.com/MatthewKaiser23/ScanCryption/assets/91264497/81d8a279-1c2b-4208-a96c-a3cbd4bb15a6)

- ![Demo Video 3](https://github.com/MatthewKaiser23/ScanCryption/assets/91264497/f1971fb4-1ebb-4e6f-b0c0-0f860cc24796)

## External Resources

This section is to acknowledge and thank the following external resources that inspired and contributed to the development of this project:

- [User Interface](https://www.youtube.com/watch?v=VigFgq7h2X0) this was used for the layout of the user interface
- [api-ninjas](https://api-ninjas.com) provided valuable insights on how to make the API call
- [Stack Overflow](https://stackoverflow.com/) The community's discussion and solutions were incredibly helpful when faced with various challenges.

## Extra points

Please note that I have provided my API key for all to use. Make sure to use your own, which can be found at [api-ninjas](https://api-ninjas.com)
