

import React, { useEffect } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  TouchableOpacity,
} from 'react-native';
// import  ImmersiveMode  from 'react-native-immersive-mode';
import { NativeModules } from 'react-native';
// import Kiosk from "react-native-kiosk";


function App() {
  const { KioskAppModel } = NativeModules;

  const handleFullscreen = () => {
    KioskAppModel.enterLockTask("dhodd;aj","ehjfoiiodf");
    // Kiosk.fullscreen();
    // NativeModules.KioskAppModel.createCalendarEvent("dhodd;aj","ehjfoiiodf")
    // ImmersiveMode.fullLayout(true);
    
  };

  const handleExitFullscreen = () => {
    KioskAppModel.exitLockTask("dhodd;aj","ehjfoiiodf");
    // ImmersiveMode.fullLayout(false);
    // Kiosk.exitFullscreen();
  };

  return (
    <SafeAreaView style={styles.sectionContainer}>
      <TouchableOpacity style={styles.sectionDescription} onPress={() => {
        // Immersive.on()
        handleFullscreen()
        // Immersive.setImmersive(true)
        console.log("----");
      }}>
        <Text>on kiosk</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.sectionDescription} onPress={() => {
        handleExitFullscreen()
        // Immersive.off()
        // Immersive.setImmersive(false)
      }}>
        <Text>off kiosk</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 200,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
