package com.example.musicplayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.musicplayer.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

}