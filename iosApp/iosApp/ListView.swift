import SwiftUI
import KMPNativeCoroutinesAsync
import KMPObservableViewModelSwiftUI
import Shared

struct ListView: View {
    @StateViewModel
    var viewModel = ListViewModel(
        museumRepository: KoinDependencies().museumRepository
    )

    let columns = [
        GridItem(.adaptive(minimum: 120), alignment: .top)
    ]

    var body: some View {
        ZStack {
            if !viewModel.objects.isEmpty {
                NavigationStack {
                    ScrollView {
                        LazyVGrid(columns: columns, alignment: .leading, spacing: 20) {
                            ForEach(viewModel.objects, id: \.self) { item in
                                NavigationLink(value: item.objectID) {
                                    ObjectFrame(obj: item)
                                }
                                .buttonStyle(PlainButtonStyle())
                            }
                        }
                        .padding(.horizontal)
                    }
                    .navigationDestination(for: Int32.self) { objectId in
                        DetailView(objectId: objectId)
                    }
                }
            } else {
                Text("No data available")
            }
        }
    }
}

struct ObjectFrame: View {
    let obj: MuseumObject

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            AsyncImage(url: URL(string: obj.primaryImageSmall)) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                        .frame(maxWidth: .infinity)
                        .aspectRatio(1, contentMode: .fit)
                case .success(let image):
                    image
                        .resizable()
                        .scaledToFill()
                case .failure:
                    EmptyView()
                        .frame(maxWidth: .infinity)
                        .aspectRatio(1, contentMode: .fit)
                }
            }
            .aspectRatio(1, contentMode: .fit)
            .clipped()

            Text(obj.title)
                .font(.headline)

            Text(obj.artistDisplayName)
                .font(.subheadline)

            Text(obj.objectDate)
                .font(.caption)
        }
    }
}
