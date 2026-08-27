import { useState } from 'react';
import { MuseumList } from './components/MuseumList/MuseumList.tsx';
import { MuseumDetail } from './components/MuseumDetail/MuseumDetail.tsx';

export function App() {
  const [selectedObjectId, setSelectedObjectId] = useState<number | null>(null);

  if (selectedObjectId === null) {
    return <MuseumList onObjectClick={setSelectedObjectId} />;
  }

  return (
    <MuseumDetail
      objectId={selectedObjectId}
      onBackClick={() => setSelectedObjectId(null)}
    />
  );
}
