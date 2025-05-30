import ComposeApp
import SwiftUI

@main
struct iOSApp: App {
    // KMP - Koin Call
    init() {
        InitKoinKt.doInitKoin(appDeclaration: { _ in })
    }

    var body: some Scene {
        WindowGroup {
            ContentView().ignoresSafeArea(.all).ignoresSafeArea(.keyboard)
        }
    }
}
