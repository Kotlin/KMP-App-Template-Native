import SwiftUI
import KMPNativeCoroutinesAsync
import Shared

struct ListView: View {
    @StateObject private var viewModelStoreOwner = IosViewModelStoreOwner()

    @State private var objects: [MuseumObject] = []

    let columns = [
        GridItem(.adaptive(minimum: 180), alignment: .top)
    ]

    private var viewModel: ListViewModel {
        viewModelStoreOwner.viewModel(factory: KoinDependencies().listViewModelFactory)
    }

    var body: some View {
        ZStack {
            if !objects.isEmpty {
                NavigationStack {
                    ScrollView {
                        LazyVGrid(columns: columns, spacing: 0) {
                            ForEach(objects, id: \.self) { item in
                                NavigationLink(value: item.objectID) {
                                    ObjectFrame(obj: item)
                                }
                                .buttonStyle(PlainButtonStyle())
                            }
                        }
                    }
                    .navigationDestination(for: Int32.self) { objectId in
                        DetailView(objectId: objectId)
                    }
                }
            } else {
                EmptyScreenContent()
            }
        }
        .task {
            await observeObjects()
        }
    }

    @MainActor
    private func observeObjects() async {
        do {
            let stream = asyncSequence(for: viewModel.objectsFlow)
            for try await newObjects in stream {
                self.objects = newObjects
            }
        } catch {
            print("Failed observing objects: \(error)")
        }
    }
}

struct EmptyScreenContent: View {
    var body: some View {
        Text("No data available")
            .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

struct ObjectFrame: View {
    let obj: MuseumObject

    var body: some View {
        VStack(alignment: .leading) {
            Color(white: 0.9)
                .aspectRatio(1, contentMode: .fit)
                .overlay(
                    AsyncImage(url: URL(string: obj.primaryImageSmall)) { phase in
                        switch phase {
                        case .success(let image):
                            image
                                .resizable()
                                .scaledToFill()
                        default:
                            EmptyView()
                        }
                    }
                )
                .clipped()

            Spacer().frame(height: 2)

            Text(obj.title)
                .font(.headline)

            Text(obj.artistDisplayName)
                .font(.subheadline)

            Text(obj.objectDate)
                .font(.caption)
        }
        .padding(8)
    }
}
