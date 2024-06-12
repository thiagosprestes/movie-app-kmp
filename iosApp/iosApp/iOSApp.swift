import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
   // KMP - Koin Call
    init() {
        InitKoinKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView().ignoresSafeArea(.all).ignoresSafeArea(.keyboard)
		}
	}
}
