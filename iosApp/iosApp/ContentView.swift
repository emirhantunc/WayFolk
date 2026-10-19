import UIKit
import SwiftUI
import Shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Self.Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Self.Context) {}
}

struct ContentView: View {
    @State private var statusBarHidden = false

    var body: some View {
        ComposeView()
            .ignoresSafeArea()
            .statusBarHidden(statusBarHidden)
            .onAppear {
                MainViewControllerKt.setStatusBarVisibilityListener { visible in
                    statusBarHidden = !visible.boolValue
                }
            }
    }
}
